package com.lwx.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.management.entity.EducationBackground;
import com.lwx.management.entity.Experience;
import com.lwx.management.entity.Information;
import com.lwx.management.entity.vo.InformationQuery;
import com.lwx.management.entity.vo.InformationShow;
import com.lwx.management.entity.vo.InformationVo;
import com.lwx.management.mapper.InformationMapper;
import com.lwx.management.service.EducationBackgroundService;
import com.lwx.management.service.ExperienceService;
import com.lwx.management.service.InformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService {

    @Autowired
    private InformationService informationService;

    @Autowired
    private EducationBackgroundService educationBackgroundService;

    @Autowired
    private ExperienceService experienceService;

    @Override
    public List<Information> getInformationPageList(QueryWrapper<Information> queryWrapper, long current, long limit) {
        return baseMapper.getInformationList(queryWrapper, current, limit);
    }

    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }

    @Override
    public Map<String, List> getpageInformationCondition(long current, long limit, InformationQuery informationQuery) {
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        // 多条件组合查询,mybatis学过 动态sql
        String name = informationQuery.getName();
        String level = informationQuery.getStaffType();
        String dept = informationQuery.getBelongDept();
        String begin = informationQuery.getBegin();
        String end = informationQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("staff_type", level);
        }
        if (!StringUtils.isEmpty(dept)) {
            wrapper.eq("deptno", dept);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("join_date", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("join_date", end);
        }
        wrapper.eq("is_delete", 0);
        current = (current - 1) * 10;
        List<Information> informationPageList = informationService.getInformationPageList(wrapper, current, limit);
        ArrayList<Object> countList = new ArrayList<>();
        int count = informationService.count(wrapper);
        countList.add(count);

        // 进行数据封装
        HashMap<String, List> map = new HashMap<>();
        map.put("total", countList);
        map.put("records", informationPageList);
        return map;
    }

    @Override
    public boolean addInformation(InformationVo informationvo) {
        Information information = new Information();
        try {
            formatDate(informationvo, information);
            informationService.save(information);
            String id = information.getId();

            saveOrUpdateEducationBackGround(informationvo, id);
            saveOrUpdateExperience(informationvo, id);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateInformation(InformationVo informationvo) {
        Information information = new Information();
        try {
            formatDate(informationvo, information);
            informationService.updateById(information);
            String id = information.getId();

            saveOrUpdateEducationBackGround(informationvo, id);
            saveOrUpdateExperience(informationvo, id);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public InformationShow getInformationById(String id) {
        InformationShow informationVo = new InformationShow();
        BeanUtils.copyProperties(informationService.getById(id), informationVo);

        setEduValue(informationVo, id);
        setExpValue(informationVo, id);

        return informationVo;
    }

    @Override
    public boolean removeByStaffId(String id) {
        informationService.removeById(id);
        return true;
    }

    private void saveOrUpdateEducationBackGround(InformationVo informationvo, String id) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        EducationBackground educationBackground = new EducationBackground();
        educationBackground.setId(informationvo.getId1());
        informationvo.setGraduationTime1(informationvo.getGraduationTime1().substring(0, 10));
        educationBackground.setGraduationTime(format.parse(informationvo.getGraduationTime1()));
        educationBackground.setEducation(informationvo.getEducation1());
        educationBackground.setMajor(informationvo.getMajor1());
        educationBackground.setSchool(informationvo.getSchool1());
        educationBackground.setSno(id);
        educationBackgroundService.saveOrUpdate(educationBackground);


        EducationBackground educationBackground1 = new EducationBackground();
        educationBackground1.setId(informationvo.getId2());
        if (informationvo.getGraduationTime2() != null) {
            informationvo.setGraduationTime2(informationvo.getGraduationTime2().substring(0, 10));
            educationBackground1.setGraduationTime(format.parse(informationvo.getGraduationTime2()));
        }
        educationBackground1.setEducation(informationvo.getEducation2());
        educationBackground1.setMajor(informationvo.getMajor2());
        educationBackground1.setSchool(informationvo.getSchool2());
        educationBackground1.setSno(id);
        educationBackgroundService.saveOrUpdate(educationBackground1);


        EducationBackground educationBackground2 = new EducationBackground();
        educationBackground2.setId(informationvo.getId3());
        if (informationvo.getGraduationTime3() != null) {
            informationvo.setGraduationTime3(informationvo.getGraduationTime3().substring(0, 10));
            educationBackground2.setGraduationTime(format.parse(informationvo.getGraduationTime3()));
        }
        educationBackground2.setEducation(informationvo.getEducation3());
        educationBackground2.setMajor(informationvo.getMajor3());
        educationBackground2.setSchool(informationvo.getSchool3());
        educationBackground2.setSno(id);
        educationBackgroundService.saveOrUpdate(educationBackground2);

    }

    private void saveOrUpdateExperience(InformationVo informationVo, String id) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Experience experience = new Experience();
        experience.setId(informationVo.getExp1());
        experience.setCompany(informationVo.getCompany1());
        experience.setSno(id);
        experience.setPosition(informationVo.getPosition1());
        experience.setReason(informationVo.getReason1());
        experience.setSalary(informationVo.getSalary1());
        if (informationVo.getStart1() != null) {
            informationVo.setStart1(informationVo.getStart1().substring(0, 10));
            experience.setStart(format.parse(informationVo.getGraduationTime1()));
        }
        if (informationVo.getEnd1() != null) {
            informationVo.setEnd1(informationVo.getEnd1().substring(0, 10));
            experience.setEnd(format.parse(informationVo.getGraduationTime1()));
        }
        experienceService.saveOrUpdate(experience);

        Experience experience2 = new Experience();
        experience2.setId(informationVo.getExp2());
        experience2.setCompany(informationVo.getCompany2());
        experience2.setSno(id);
        experience2.setPosition(informationVo.getPosition2());
        experience2.setReason(informationVo.getReason2());
        experience2.setSalary(informationVo.getSalary2());
        if (informationVo.getStart2() != null) {
            informationVo.setStart2(informationVo.getStart2().substring(0, 10));
            experience2.setStart(format.parse(informationVo.getGraduationTime2()));
        }
        if (informationVo.getEnd2() != null) {
            informationVo.setEnd2(informationVo.getEnd2().substring(0, 10));
            experience2.setEnd(format.parse(informationVo.getGraduationTime1()));
        }
        experienceService.saveOrUpdate(experience2);
    }

    private void formatDate(InformationVo informationvo, Information information) throws ParseException {
        if (informationvo.getJoinDate() != null && informationvo.getBornDate() != null && informationvo.getGetPositionDate() != null) {
            informationvo.setJoinDate(informationvo.getJoinDate().substring(0, 10));
            informationvo.setGetPositionDate(informationvo.getGetPositionDate().substring(0, 10));
            informationvo.setBornDate(informationvo.getBornDate().substring(0, 10));
            information.setJoinDate((new SimpleDateFormat("yyyy-MM-dd")).parse(informationvo.getJoinDate()));
            information.setGetPositionDate(new SimpleDateFormat("yyyy-MM-dd").parse(informationvo.getGetPositionDate()));
            information.setBornDate(new SimpleDateFormat("yyyy-MM-dd").parse(informationvo.getBornDate()));
            BeanUtils.copyProperties(informationvo, information);
        }
    }

    private void setEduValue(InformationShow informationVo,String id) {
        QueryWrapper<EducationBackground> educationBackgroundQueryWrapper = new QueryWrapper<>();
        educationBackgroundQueryWrapper.eq("sno", id)
                .orderByAsc("gmt_create");
        List<EducationBackground> educationBackgroundList = educationBackgroundService.list(educationBackgroundQueryWrapper);

        informationVo.setId1(educationBackgroundList.get(0).getId());
        informationVo.setEducation1(educationBackgroundList.get(0).getEducation());
        informationVo.setSchool1(educationBackgroundList.get(0).getSchool());
        informationVo.setMajor1(educationBackgroundList.get(0).getMajor());
        informationVo.setGraduationTime1(educationBackgroundList.get(0).getGraduationTime());

        informationVo.setId2(educationBackgroundList.get(1).getId());
        informationVo.setEducation2(educationBackgroundList.get(1).getEducation());
        informationVo.setSchool2(educationBackgroundList.get(1).getSchool());
        informationVo.setMajor2(educationBackgroundList.get(1).getMajor());
        informationVo.setGraduationTime2(educationBackgroundList.get(1).getGraduationTime());

        informationVo.setId3(educationBackgroundList.get(2).getId());
        informationVo.setEducation3(educationBackgroundList.get(2).getEducation());
        informationVo.setSchool3(educationBackgroundList.get(2).getSchool());
        informationVo.setMajor3(educationBackgroundList.get(2).getMajor());
        informationVo.setGraduationTime3(educationBackgroundList.get(2).getGraduationTime());
    }

    private void setExpValue(InformationShow informationVo, String id) {
        QueryWrapper<Experience> experienceQueryWrapper = new QueryWrapper<>();
        experienceQueryWrapper.eq("sno", id)
                .orderByAsc("gmt_create");
        List<Experience> educationBackgroundList = experienceService.list(experienceQueryWrapper);

        informationVo.setCompany1(educationBackgroundList.get(0).getCompany());
        informationVo.setExp1(educationBackgroundList.get(0).getId());
        informationVo.setPosition1(educationBackgroundList.get(0).getPosition());
        informationVo.setReason1(educationBackgroundList.get(0).getReason());
        informationVo.setSalary1(educationBackgroundList.get(0).getSalary());
        informationVo.setStart1(educationBackgroundList.get(0).getStart());
        informationVo.setEnd1(educationBackgroundList.get(0).getEnd());

        informationVo.setCompany2(educationBackgroundList.get(1).getCompany());
        informationVo.setExp2(educationBackgroundList.get(1).getId());
        informationVo.setPosition2(educationBackgroundList.get(1).getPosition());
        informationVo.setReason2(educationBackgroundList.get(1).getReason());
        informationVo.setSalary2(educationBackgroundList.get(1).getSalary());
        informationVo.setStart2(educationBackgroundList.get(1).getStart());
        informationVo.setEnd2(educationBackgroundList.get(1).getEnd());

    }
}
