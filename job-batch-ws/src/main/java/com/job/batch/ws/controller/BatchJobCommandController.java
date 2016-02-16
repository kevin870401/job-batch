package com.job.batch.ws.controller;

import com.otpp.batchJob.domain.command.BatchJobCreateCommandDto;
import com.otpp.batchJob.domain.command.BatchJobLogCreateCommandDto;
import com.otpp.batchJob.domain.command.BatchJobStatusUpdateCommandDto;
import com.otpp.batchJob.service.command.BatchJobCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/command/batchJob")
public class BatchJobCommandController {
    @Autowired
    private final BatchJobCommandService batchJobCommandService;


    public BatchJobCommandController(BatchJobCommandService batchJobCommandService) {
        this.batchJobCommandService = batchJobCommandService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public long createBatchJob(@RequestBody BatchJobCreateCommandDto batchJobCreateCommandDto) {
        return batchJobCommandService.createBatchJob(batchJobCreateCommandDto);
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateBatchJobStatus(@RequestBody BatchJobStatusUpdateCommandDto batchJobStatusUpdateCommandDto) {
        batchJobCommandService.updateBatchJobStatus(batchJobStatusUpdateCommandDto);
    }

    @RequestMapping(value = "/insertLog", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void getJobByStatusOrType(@RequestBody BatchJobLogCreateCommandDto batchJobLogCreateCommandDto) {
        batchJobCommandService.insertBatchJobLog(batchJobLogCreateCommandDto);
    }


}
