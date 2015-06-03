package org.oncoblocks.magpie.rest.controllers;

//import org.oncoblocks.magpie.rest.models.CopyNumberGeneCentric;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by tewei on 6/1/15.
 */
@RestController
@RequestMapping("/copynumbergenecentric")
public class CopyNumberGeneCentricController {
    @RequestMapping("")
    public String findCopyNumberGeneCentricByGeneId(@RequestParam(value = "geneId") Integer geneId){

        return String.format("Test: Input geneId is %d\n", geneId);
    }
}
