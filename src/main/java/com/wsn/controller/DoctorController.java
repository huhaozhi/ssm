package com.wsn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wsn.bean.DoctorInfo;
import com.wsn.bean.Msg;
import com.wsn.service.DoctorService;

@Controller
public class DoctorController {
	@Autowired
	private DoctorService doctor_info_service;

	/**
	 * 新增用户
	 */
	@RequestMapping(value="/insertDoctor",method=RequestMethod.GET)
	@ResponseBody
	public Msg insertDoctor(@RequestParam(value = "mac") Integer mac, @RequestParam(value = "name") String name,
			@RequestParam(value = "work_num") String work_num, @RequestParam(value = "position") String position,
			@RequestParam(value = "wage") Integer wage, @RequestParam(value = "department") String department) {
		DoctorInfo doctor = new DoctorInfo();
		doctor.setDepartment(department);
		doctor.setMac(mac);
		doctor.setName(name);
		doctor.setPosition(position);
		doctor.setWage(wage);
		doctor.setWorkNum(work_num);
		doctor_info_service.insertDoctor(doctor);
		System.out.println("mac"+mac+"name"+name+"work_num"+work_num+wage+department+position);
		return Msg.success();
	}

	/**
	 * 查询医生信息
	 */
	@RequestMapping(value="/getDoctors",method=RequestMethod.POST)
	@ResponseBody
	public Msg getDoctors() {
		List<DoctorInfo> doctors = doctor_info_service.getDoctors();
		return Msg.success().add("DoctorInfo", doctors);
	}

	/**
	 * 删除用户信息 自己新增一个删除方法
	 */
	@RequestMapping(value="/deleteDoctor",method=RequestMethod.POST)
	@ResponseBody
	// @RequestParam(value="work_num") String
	// work_num：表示从前台获取的work_num的数据放到字符串work_num中
	public Msg deleteDoctor(@RequestParam(value = "work_num") String work_num) {
		doctor_info_service.deleteDoctor(work_num);
		return Msg.success();
	}

	/**
	 * 修改员工信息
	 */
	@RequestMapping(value="/updateDoctor",method=RequestMethod.GET)
	@ResponseBody
	public Msg updateDoctor(@RequestParam(value = "mac") Integer mac, @RequestParam(value = "name") String name,
			@RequestParam(value = "work_num") String work_num, @RequestParam(value = "position") String position,
			@RequestParam(value = "wage") Integer wage, @RequestParam(value = "department") String department) {
		DoctorInfo doctor = new DoctorInfo();
		doctor.setDepartment(department);
		doctor.setMac(mac);
		doctor.setName(name);
		doctor.setPosition(position);
		doctor.setWage(wage);
		doctor.setWorkNum(work_num);
		doctor_info_service.updateDoctor(doctor);
		return Msg.success();
	}
	
	/**
	 * 按照工号查询
	 */
	@RequestMapping(value="getDocByNum",method=RequestMethod.GET)
	@ResponseBody
	public Msg getDoctorByNum(@RequestParam(value="work_num" )String work_num){
		List<DoctorInfo> doctors = doctor_info_service.getDoctorsByNum(work_num);
		return Msg.success().add("DoctorInfo", doctors);
	}

}
