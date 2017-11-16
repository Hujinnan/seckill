package com.shou.seckill.web;

import com.shou.seckill.dto.ExportUrl;
import com.shou.seckill.dto.SeckillExecution;
import com.shou.seckill.dto.SeckillResult;
import com.shou.seckill.entity.SecKill;
import com.shou.seckill.enums.SeckillStateEnum;
import com.shou.seckill.exception.RepeatException;
import com.shou.seckill.exception.SeckillCloseException;
import com.shou.seckill.exception.SeckillException;
import com.shou.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by xiaoz on 2017/11/15.
 */

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
     private SeckillService seckillService;

    @RequestMapping(name = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<SecKill> secKills = seckillService.getSeckillList();
        model.addAttribute("list",secKills);
        return "list";
    }

    @RequestMapping(name = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if(seckillId == null){
            return "redirect:/seckill/list";
        }
        SecKill secKill = seckillService.getById(seckillId);
        if(secKill == null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",secKill);
        return "detail";
    }

    @RequestMapping(name = "/{seckillId/exposer}",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<ExportUrl> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<ExportUrl> result = null;
        try {
            ExportUrl exportUrl = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult(true,exportUrl);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            result = new SeckillResult(false,e.getMessage());
        }

        return result;
    }

    @RequestMapping(name = "/{seckillId}/{md5}/excution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> excute(@PathVariable("seckillId") Long seckillId,
                                                  @PathVariable("md5") String md5,
                                                  @CookieValue(value = "killPhone",required = false) Long phone){
        SeckillResult<SeckillExecution> result = null;
        if(phone == null){
            result = new SeckillResult<SeckillExecution>(false,"未注册");
        }

        try {
            SeckillExecution seckillException = seckillService.executeSeckill(seckillId,phone,md5);
            result = new SeckillResult<SeckillExecution>(true,seckillException);
        }catch (RepeatException e){
            SeckillExecution seckillException = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
            result = new SeckillResult<SeckillExecution>(false,seckillException);
        }catch (SeckillCloseException e){
            SeckillExecution seckillException = new SeckillExecution(seckillId, SeckillStateEnum.END);
            result = new SeckillResult<SeckillExecution>(false,seckillException);
        } catch (SeckillException e) {
            logger.error(e.getMessage(),e);
            SeckillExecution seckillException = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
            result = new SeckillResult<SeckillExecution>(false,seckillException);
        }
        return result;
    }

    @RequestMapping(name = "/time/now", method = RequestMethod.GET)
    public SeckillResult<Long> time(){
        Date date = new Date();
        return new SeckillResult(true,date);
    }
}
