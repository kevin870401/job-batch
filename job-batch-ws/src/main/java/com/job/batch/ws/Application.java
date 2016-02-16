package com.job.batch.ws;

import com.job.batch.ws.config.ControllerConfiguration;
import com.job.batch.ws.config.WebConfiguration;
import com.otpp.batchJob.domain.config.DomainConfig;
import com.otpp.batchJob.service.command.CommandServiceConfig;
import com.otpp.batchJob.service.query.QueryServiceConfig;
import com.otpp.jobBatch.data.config.DataContext;
import com.otpp.jobBatch.data.entity.BatchJob;
import com.otpp.jobBatch.data.entity.BatchJobLog;
import com.otpp.jobBatch.data.entity.BatchJobParameter;
import com.otpp.jobBatch.data.entity.enums.DossierBatchJobStatus;
import com.otpp.jobBatch.data.repository.BatchJobLogRepository;
import com.otpp.jobBatch.data.repository.BatchJobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@SpringBootApplication
@Import({ControllerConfiguration.class, QueryServiceConfig.class, CommandServiceConfig.class, WebConfiguration.class, DomainConfig.class, DataContext.class})
public class Application {

    private static final UUID UUID1 = UUID.fromString("07ac789c-d19c-11e5-ab30-625662870761");
    private static final UUID UUIDNEW = UUID.fromString("07ac8436-d19c-11e5-ab30-625662870761");
    private static final String BTCHANNSTATPROTO = "BTCHANNSTATPROTO";
    private static final short FIRST_PRIORITY = 1;
    private static final String A_SIMPLE_PSOB_TASK = "a simple psob task";
    private static final String IRN = "IRN";
    private static final String OWNER = "OWNER";
    private static EmbeddedDatabase db;
    public static void main(String[] args) {



            db = new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .addScript("classpath:drop-db.sql")
                    .addScript("classpath:create-db.sql")
                    .addScript("classpath:insert-db.sql")
                    .build();

        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(BatchJobRepository repository,BatchJobLogRepository batchJobLogRepository) {
        return (args) -> {

            BatchJobParameter parameter1 = new BatchJobParameter();
            BatchJobParameter parameter2 = new BatchJobParameter();

            parameter1.setParameterKey(IRN);
            parameter1.setParameterValue("123456789");

            parameter2.setParameterKey(OWNER);
            parameter2.setParameterValue("psob admin");

            BatchJob expected = new BatchJob();
            //expected.setId(10);
            expected.setUuid(UUIDNEW);
            expected.setType(BTCHANNSTATPROTO);
            expected.setPriority(FIRST_PRIORITY);
            expected.setDescription(A_SIMPLE_PSOB_TASK);
            expected.setStatus(DossierBatchJobStatus.PND);
            expected.setResult("");
            List<BatchJobParameter> parameters = new ArrayList<BatchJobParameter>();
            parameter1.setJob(expected);
            parameter2.setJob(expected);
            parameters.add(parameter1);
            parameters.add(parameter2);
            expected.setParameters(parameters);
            BatchJob result = repository.saveAndFlush(expected);
            BatchJob batchJob = repository.findById(1);
            log.info("created batchjob----" + batchJob.toString());

            BatchJobLog batchJobLog = new BatchJobLog();
            batchJobLog.setType("App");
            batchJobLog.setData("new log");
            batchJobLog.setJobId(1);
            BatchJobLog batchJobLog1 = batchJobLogRepository.saveAndFlush(batchJobLog);
            //log.info("created batchJobLog----" + batchJobLog1.toString());
        };
    }
}
