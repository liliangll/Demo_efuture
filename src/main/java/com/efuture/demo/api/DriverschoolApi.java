package com.efuture.demo.api;

import java.util.List;
import com.efuture.demo.annotation.LoginRequired;
import com.efuture.demo.model.CityArea;
import com.efuture.demo.model.DriverSchool;
import com.efuture.demo.service.CityAreaService;
import com.efuture.demo.service.DriverSchoolService;
import com.efuture.demo.util.JsonResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api/driver")
@Slf4j
public class DriverschoolApi {

    @Autowired
    private DriverSchoolService driverSchoolService;
    public void setDriverSchoolService(DriverSchoolService driverSchoolService) {
        this.driverSchoolService = driverSchoolService;
    }

    @Autowired
    private CityAreaService cityAreaService;
    public void setCityAreaService(CityAreaService cityAreaService) {
        this.cityAreaService = cityAreaService;
    }

    /**
     *修改
     * */
    @LoginRequired
    @PutMapping (value="/toUpdate.do/{did}")
    @ApiOperation(value="修改",notes = "根据did修改驾校信息")
    public JsonResponse toUpdate(DriverSchool school, @PathVariable("did") Integer did) {
        List<CityArea> clist=this.cityAreaService.getAllCityAreas();
        if (!school.equals("") || school != null) {
            try {
                driverSchoolService.update(school);
                log.info("------------toUpdate----------- {}", did);
                if (did > 0) {
                    return JsonResponse.ok("修改成功");
                } else {
                    return JsonResponse.notOk(500, "修改失败");
                }
            } catch (Exception e) {
                log.debug(e.toString());
                return JsonResponse.notOk(500, "修改失败");
            }
        }
        return JsonResponse.ok(clist);
    }


    /**
     *	进入新增
     */
    @ApiOperation(value="新增",notes = "新增驾校信息")
    @LoginRequired
    @PostMapping (value="/toInsert.do")
    public JsonResponse toInsert(DriverSchool school) {
        List<CityArea> clist = this.cityAreaService.getAllCityAreas();//得到所有城区
        if (!clist.equals("") || clist != null) {
            try {
                int id = driverSchoolService.insert(school);
                log.info("------------toInsert----------- {}", id);
                if (id > 0) {
                    return JsonResponse.ok("新增成功");
                } else {
                    return JsonResponse.notOk(500, "新增失败");
                }
            } catch (Exception e) {
                log.debug(e.toString());
                return JsonResponse.notOk(500, "新增失败");
            }
        }
        return JsonResponse.ok(clist);
    }

    /*
     * 分页显示
     * @param
     * @param
     * @return
     */
    @ApiOperation(value="分页查询",notes = "查询驾校信息")
    @LoginRequired
    @GetMapping(value="/toPager.do/{pageNum}/{pageSize}")
    public JsonResponse toPager(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        try {
            List<DriverSchool> clist = this.driverSchoolService.findAllUser(pageNum, pageSize);
            //获取查询结果
            PageInfo<DriverSchool> pageInfo = new PageInfo<DriverSchool>();
            return JsonResponse.ok(pageInfo.getTotal(),clist);
        } catch (Exception e) {
            log.debug(e.toString());
            return JsonResponse.SERVER_ERR;
        }
    }
    /**
     *
     * 根据name查询信息
     * @return
     * */
    @ApiOperation(value="按条件查询查询",notes = "根据name查询驾校信息")
    @LoginRequired
    @GetMapping (value="/toDname.do/{dname}")
    public JsonResponse toDname(@PathVariable("dname") String dname){
        try {
            return JsonResponse.ok(this.driverSchoolService.selectDname(dname));
        } catch (Exception e) {
            log.debug(e.toString());
            return JsonResponse.SERVER_ERR;
        }
    }

    /*
     *
     * 获取下拉框
     * @return*/
    @LoginRequired
    @ApiOperation(value="下拉框",notes = "获取下拉框信息")
    @GetMapping(value="/toXia.do")
    public JsonResponse doXia(){
        try {
            List<CityArea> clist=this.cityAreaService.getAllCityAreas();//得到所有城区
            return JsonResponse.ok(clist);
        } catch (Exception e) {
            log.error(e.getMessage());
            return JsonResponse.notOk(JsonResponse.ERR, e.getMessage());
        }
    }

    /*
     *
     * 删除
     */
    @LoginRequired
    @ApiOperation(value="删除",notes = "删除驾校表信息")
    @DeleteMapping(value="/doDel.do/{did}")
    public JsonResponse doDel(@PathVariable Integer did){
        try {
            int id = driverSchoolService.del(did);
            log.info("------------doDel----------- {}", id);
            if (id > 0) {
                return JsonResponse.ok("删除成功");
            } else {
                return JsonResponse.notOk(500, "删除失败");
            }
        } catch (Exception e) {
            log.debug(e.toString());
            return JsonResponse.notOk(500, "删除失败");
        }
    }
}

