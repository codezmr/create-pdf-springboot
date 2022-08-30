package com.codezmr.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.repository.Repository;

import com.codezmr.spring.batch.entity.Sales;
import com.codezmr.spring.batch.repository.SalesRepository;

import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private SalesRepository salesRepository;

	@Bean
	public FlatFileItemReader<Sales> reader() {

		FlatFileItemReader itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource("src/main/resources/sales.csv"));

		itemReader.setName("csvReader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		
		return itemReader;
	}

	private LineMapper lineMapper() {

		DefaultLineMapper<Sales> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("region", "country", "itemType", "salesChannel", "orderPriority", "orderDate", "orderId",
				"shipDate", "unitsSold", "unitPrice", "unitCost", "totalRevenue", "totalCost", "totalProfit");

		BeanWrapperFieldSetMapper<Sales> feildSetMapper = new BeanWrapperFieldSetMapper<>();
		feildSetMapper.setTargetType(Sales.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(feildSetMapper);

		return lineMapper;
	}
	
	@Bean
	public SalesProcessor processor() {
		return new SalesProcessor();
	}
	
	
	@Bean
	public RepositoryItemWriter<Sales> writer(){
		
		RepositoryItemWriter<Sales> writer = new RepositoryItemWriter<>();
	
		writer.setRepository(salesRepository);
		writer.setMethodName("save");
		return writer;
	
	}
	
	@Bean
	public Step step1() {
		
		return stepBuilderFactory.get("csv-step").<Sales, Sales>chunk(1000)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.taskExecutor(taskExecutor())
				.build();
	}
	
	
	@Bean
	public Job runJob() {
		
		return jobBuilderFactory.get("importSales")
				.flow(step1())
				.end()
				.build();
	}
	
	@Bean
	public TaskExecutor taskExecutor() {
		
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(100);
		return asyncTaskExecutor;
		
	}
	
	
}


























