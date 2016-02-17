package com.otpp.batchJob.ws.controller;

import com.otpp.batchJob.domain.query.BatchJob;
import com.otpp.batchJob.domain.query.BatchJobLog;
import com.otpp.batchJob.service.query.BatchJobLogQueryService;
import com.otpp.batchJob.service.query.BatchJobQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/query/batchJob")
public class BatchJobQueryController {
    @Autowired
    private final BatchJobQueryService batchJobQueryService;
    @Autowired
    private final BatchJobLogQueryService batchJobLogQueryService;

    public BatchJobQueryController(BatchJobQueryService batchJobQueryService, BatchJobLogQueryService batchJobLogQueryService){
        this.batchJobQueryService= batchJobQueryService;
        this.batchJobLogQueryService=batchJobLogQueryService;
    }

    @RequestMapping(value = "/{id}/logs",method = RequestMethod.GET)
    @ResponseBody
    public List<BatchJobLog> getLogByJobId(@PathVariable long id,@RequestParam("page") int page,@RequestParam("size") int size) {
        return batchJobLogQueryService.findByJobId(id,page,size);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public BatchJob getJobById(@PathVariable long id) {
        return batchJobQueryService.findById(id);
    }

    @RequestMapping(value = {"","searchByStatusOrType","search"},method = RequestMethod.GET)
    @ResponseBody
    public List<BatchJob> getJobByStatusOrType(@RequestParam(value="status", required=false) String status,@RequestParam(value = "type", required=false) String type,@RequestParam("page") int page,@RequestParam("size") int size) {
        return batchJobQueryService.findByStatusOrType(status, type, page, size);
    }

    @RequestMapping(value = {"countByStatusOrType","count"},method = RequestMethod.GET)
    @ResponseBody
    public long countByStatusOrType(@RequestParam(value="status", required=false) String status,@RequestParam(value = "type", required=false) String type) {
        return batchJobQueryService.countByStatusOrType(status, type);
    }
}
