package com.lsjbc.vdtts.service.impl;

import com.alibaba.fastjson.JSON;
import com.lsjbc.vdtts.constant.AccountType;
import com.lsjbc.vdtts.dao.*;
import com.lsjbc.vdtts.dao.mapper.AccountMapper;
import com.lsjbc.vdtts.dao.mapper.ExamResultMapper;
import com.lsjbc.vdtts.dao.mapper.StudentMapper;
import com.lsjbc.vdtts.entity.*;
import com.lsjbc.vdtts.pojo.bo.aliai.SMS;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.pojo.vo.StudentRegister;
import com.lsjbc.vdtts.service.intf.AsyncService;
import com.lsjbc.vdtts.service.intf.LinkService;
import com.lsjbc.vdtts.service.intf.StudentService;
import com.lsjbc.vdtts.utils.FileTools;
import com.lsjbc.vdtts.utils.Tool;
import com.lsjbc.vdtts.utils.baidu.baiduTools.face.ManageFace;
import com.lsjbc.vdtts.utils.baidu.baiduTools.face.SearchFace;
import com.lsjbc.vdtts.utils.baidu.baiduTools.face.TFaceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Service(StudentServiceImpl.NAME)
public class StudentServiceImpl implements StudentService {

	public static final String NAME = "StudentService";

	@Autowired
	public StudentMapper studentMapper;
	@Autowired
	private ExamResultMapper examResultMapper;

	@Autowired
	private TeacherDao teacherDao;

	@Resource(name = StudentDao.NAME)
	private StudentDao studentDao;

	@Resource(name = AccountDao.NAME)
	private AccountDao accountDao;

	@Resource(name = ExamResultDao.NAME)
	private ExamResultDao examResultDao;

	@Resource(name = LinkServiceImpl.NAME)
	private LinkService linkServive;

	@Resource(name = SMS.NAME)
	private SMS sms;

	@Resource(name = ExamErrorDao.NAME)
	private ExamErrorDao examErrorDao;

	@Resource
	private AsyncService asyncService;

	/**
	 * student 里面所有的属性将会作为查询条件
	 * page 要查询的特定页数
	 * limit 每页条数
	 * <p>
	 * List<Student 最终返回的查询结果
	 *
	 * @author 周永哲
	 */
	@Override
	public List<Student> selectAllInfo(Student student, int page, int limit) {
		List<Student> selectAllInfo =studentMapper.selectAllInfo(student,page,limit);
		return selectAllInfo;
	}

	/*
	 *@Description:
	 *@Author:周永哲
	 *@Param:
	 *@return:
	 *@Date:2020/6/8 15860799877
	 **/
	@Override
	public int selectStudentCount(Student student) {
		int selectCount = studentMapper.selectStudentCount(student);
		return selectCount;
	}

	/*
	 *@Description:
	 *@Author:周永哲
	 *@Param:
	 *@return:
	 *@Date:2020/6/8 15860799877
	 **/
	@Override
	public int resetPwd(String studentId) {
		int resetPwd = studentMapper.resetPwd(studentId);
		return resetPwd;
	}
	@Override
	public int insertstudent(Student student) {
		int inserttudent = studentMapper.insertstudent(student);
		return inserttudent;
	}

	@Override
	public int updatestudent(Student student) {
		int updatestudent = studentMapper.updatestudent(student);
		return updatestudent;
	}



	@Override
	public int deletestudent(Student student) {
		int deletestudent = studentMapper.deletestudent(student);
		return deletestudent;
	}

	@Override
	public Integer studentExamCount1() {
		return studentMapper.studentExamCount1();
	}

	@Override
	public Integer studentExamCount2() {
		return studentMapper.studentExamCount2();
	}

	@Override
	public Integer studentExamCount3() {
		return studentMapper.studentExamCount3();
	}

	@Override
	public Integer studentExamCount4() {
		return studentMapper.studentExamCount4();
	}


	/*
	 *@Description:学生表查询
	 *@Author:陈竑霖
	 *@Param:
	 *@return:
	 *@Date:2020/6/8 1591587038161
	 **/
	@Override
	public LayuiTableData selectList(Student student, int page, int pageSize) {
		int start = (page - 1) * pageSize;//计算出起始查询位置
		if(start<0){
			start=0;
		}
		List<Student> list = studentMapper.selectList(student, start, pageSize);
		int count = studentMapper.selectListCount(student);

        LayuiTableData layuiData = new LayuiTableData();
		if (list.size() > 0) {
			layuiData.setCode(0);
			layuiData.setMsg("");
			layuiData.setCount(count);
			layuiData.setData(list);
			System.out.println(student);
		} else {
			layuiData.setCode(1);
			layuiData.setMsg("查询失败");
		}
		return layuiData;
	}

    @Override
    /*
     *@Description:
     *@Author:李浪_191019
     *@Param:[student]
     *@return:int
     *@Date:2020/6/9 1:27
     **/
    public int registerStudent(Student student){
	    return studentMapper.addStudentMessage(student);
    }

    /*
     *@Description:查询驾校内学员的
     *@Author:刘海
     *@Param:
     *@return:
     *@Date:2020/6/15 22:58
     **/
	@Override
	public LayuiTableData findStudenList(HttpServletRequest request, String page, String limit, String sName) {
		int pageSize = Integer.parseInt(limit);
		int start = (Integer.parseInt(page)-1)*pageSize;//计算从数据库第几条开始查
		School school = (School) request.getSession().getAttribute("school");
		LayuiTableData layuiTableData = new LayuiTableData();
		List<Student> studentList = studentMapper.findStudenList(school.getSId(),start,pageSize,sName);
		System.out.println("studentList"+studentList);
		int count = studentMapper.findStudentCount(1,sName);
		layuiTableData.setData(studentList);
		layuiTableData.setCount(count);
		return layuiTableData;
	}
//修改密码
	@Override
	public ResultData updatestudentPwd(HttpServletRequest request) {
		ResultData resultData = null;
		Tool tool = new Tool();
		Student student = (Student) request.getSession().getAttribute("student");
		String pwd = studentMapper.findstudentPwd(student.getSAccountId());
		String  oldPwd = tool.createMd5(request.getParameter("oldPwd"));
		String  newPwd = tool.createMd5(request.getParameter("newPwd"));
		String  repeatPwd = tool.createMd5(request.getParameter("repeatPwd"));
		System.out.println("pwd"+pwd);
		if(newPwd.equals(repeatPwd)){
			if(oldPwd.equals(pwd)){
				int num = studentMapper.updatestudentPwd(student.getSAccountId(),newPwd);
				resultData = ResultData.error(1,"密码修改成功");
			}else{
				resultData = ResultData.error(-1,"旧密码输入错误");
			}
		}else{
			resultData = ResultData.error(-1,"新密码与重复输入密码不同");
		}
		return resultData;
	}

	@Override
	public ResultData updateStudentApplyState(Integer sId) {
		ResultData resultData = null;
		int num = studentMapper.updateApplyState(sId);
		if(num>0){
			resultData = ResultData.success(1,"已成功审核学员的报名信息");
		}else{
			resultData = ResultData.success(-1,"未找到该学员信息");
		}
		return resultData;
	}

	@Override
	public ResultData updateStudentTeacherId(Integer sTeacherId, Integer sId) {
		Student student = studentMapper.findTeacher(sId);
		Teacher teacher = teacherDao.getById(sTeacherId);
		ResultData resultData = null;
		if(teacher.getTTeach().equals("true")||teacher.getTLock().equals("true")){
			if (sTeacherId != 0) {
				if (student.getSTeacherId() != null) {
					resultData = ResultData.error(-2, "该学员已分配教练，不能重新分配");
				} else {
					int num = studentMapper.updateStudentTeacherId(sTeacherId, sId);
					if (num > 0) {
						resultData = ResultData.success(1, "您已成功为该学员分配教练");
					} else {
						resultData = ResultData.error(-1, "未找到该教练信息");
					}
				}
			} else {
				resultData = ResultData.error(-1, "请选择教练");
			}
		}else{
			resultData = ResultData.error(-1, "该教练已被禁用或者已被限制");
		}
		return resultData;
	}

	/**
	 * 学员登录
	 *
	 * @param account 账号和密码对象
	 * @param request request域
	 * @return 结果集合
	 */
	@Override
	public ResultData studentLogin(Account account, HttpServletRequest request) {

		if (account == null || account.getAAccount() == null || account.getAPassword() == null) {
			return ResultData.error("账号或密码错误，请重试");
		}

		Tool tool = new Tool();

		account.setAPassword(tool.createMd5(account.getAPassword()));

		Account token = accountDao.login(account);

		if (token == null) {
			return ResultData.error("账号或密码错误，请重试");
		}

		if (!token.getAType().equals(AccountType.STUDENT)) {
			return ResultData.error("账号或密码错误，请重试");
		}
		Student student = studentDao.getStudentByAccountId(token.getAId());

		asyncService.readExamError(student.getSId());

		request.getSession().setAttribute("student", student);
		request.getSession().setAttribute("account", token);


		ResultData resultData = ResultData.success("登录成功", "url", "student/main");
		resultData.put("username", student.getSName());
		resultData.put("sid", student.getSId());
		return resultData;
	}



	/**
	 * 学员注册流程
	 *
	 * @param register 注册提供的信息对象
	 * @param map      ModelAndView中的属性键值对
	 * @param request  Request域
	 * @return 跳转的路径
	 * @author JX181114 --- 郑建辉
	 */
	@Override
	public String studentRegister(StudentRegister register, Map<String, Object> map, HttpServletRequest request) {

		//开始检测验证码有效性
		String checkVcResult = sms.checkRegisterVC(request,register.getPhone(),register.getCode(),false);

		//如果验证不通过，返回前端
		if(!"验证通过".equals(checkVcResult)){
			register.putInfoAndMsgToMap(map,checkVcResult);
			return "/pages/index/register";
		}

		//更具提供的信息生成Account对象
		Account token = register.generateAccount();

		//开始尝试性的向数据库中插入数据，如果插入成功，返回1
		Integer row = accountDao.insertIfNotExist(token);

		//如果插入失败，返回到前端
		if(row==0){
			register.putInfoAndMsgToMap(map,"该账号已被注册");
			return "/pages/index/register";
		}

		//清除session中的数据
		request.getSession().removeAttribute(SMS.PHONE);
		request.getSession().removeAttribute(SMS.VC_TYPE_REGISTER);

		//根据提供的信息和账号ID来生成学生对象
		Student student = register.generateStudent(token.getAId());

		Integer row2 = studentDao.add(student);

		if(row2==1){
			examResultDao.add(ExamResult.builder().erStudentId(student.getSId()).build());
		}

		map.put("zjh_msg","注册成功");
		map.put("iframeUrl", "/student/login");
		map.put("linkList", linkServive.getFooterFriendLink());

		return "/pages/index/student";
	}

    @Autowired
    private ManageFace manageFace;
    @Autowired
    private TFaceMethod tFaceMethod;
	@Override
    /*
     *@Description:加入学生人脸
     *@Author:李浪_191019
     *@Param:[base64, sId]
     *@return:com.lsjbc.vdtts.pojo.vo.ResultData
     *@Date:2020/6/22 22:20
     **/
	public ResultData AddFace(String base64, int sId){
        Map<String,Object> map = null;
        ResultData resultData = ResultData.success();
        MultipartFile multipartFile = new FileTools().base64ToMultipartFile(base64);
        if (tFaceMethod.checkFaceExtension(multipartFile)) {//检测图片格式合法性
            List<Map<String,Object>> list = manageFace.getFaceList(sId);//获取用户的人脸信息
            //当图片用户图片数量大于等于20张时，就删除第一张图片后再添加新的图片
            if(list.size() >= 20){
                for(Map map2:list){
                    String face_token = map2.get("face_token").toString();
                    manageFace.delete(face_token, sId);
                }
            }
            try{
                map = manageFace.add(base64.split(",")[1], sId);//向第三方加入数据(人脸)
                if (map.size() > 0) {
                    System.out.println("加入人脸识别成功");
                    resultData.setMsg("加入人脸识别成功");
                    if(map.size()==20) {
                        resultData.setCode(list.size());
                    } else{
                        resultData.setCode(list.size()+1);//人脸张数
                    }

                } else {
                    System.out.println("加入人脸识别失败");
                    resultData.setMsg("加入人脸识别失败");
                    resultData.setCode(list.size());
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            System.out.println("图片格式非法！------>\n"+tFaceMethod.checkFaceExtension(multipartFile));
        }
        System.out.println("添加后返回map = "+ JSON.toJSONString(map));//
        return resultData;
    }

    @Autowired
    private SearchFace searchFace;
	@Autowired
    private AccountMapper accountMapper;
    /*
     *@Description:刷脸登录
     *@Author:李浪_191019
     *@Param:[request, base64]
     *@return:com.lsjbc.vdtts.pojo.vo.ResultData
     *@Date:2020/6/22 22:56
     **/
    @Override
    public ResultData FaceLogin(HttpServletRequest request, String base64){
        ResultData resultData = new ResultData();
        int sId = searchFace.searchFace(base64.split(",")[1]);
        try{
            Student student = studentMapper.findTeacher(sId);
            request.getSession().setAttribute("student",student);
            Account account = new Account();
            account.setAId(student.getSAccountId());
            account = accountMapper.selectAccount(account);
            request.getSession().setAttribute("account",account);
            request.getSession().setAttribute("aId",account.getAId());
            resultData.setMsg("登录成功");
        } catch (Exception e){
            e.printStackTrace();
            resultData.setMsg("登录失败");
        }
	    return resultData;
    }

	/**
	 * 学员修改手机号流程
	 *
	 * @param request Request域
	 * @param phone   要修改的新手机号
	 * @param code    验证码
	 * @return 修改结果
	 * @author JX181114 --- 郑建辉
	 */
	@Override
	public ResultData studentUpdatePhone(HttpServletRequest request, String phone, String code) {

		Student student = (Student) request.getSession().getAttribute("student");

		//先把新手机号和旧手机号做判断，如果两者相同，直接返回
		if(phone.equals(student.getSPhone())){
			//清除session中的数据
			request.getSession().removeAttribute(SMS.PHONE);
			request.getSession().removeAttribute(SMS.VC_TYPE_UPDATE);
			return ResultData.success("修改成功");
		}

		//开始验证短信验证码
		String checkVcResult = sms.checkUpdateVC(request,phone,code,false);

		//如果验证不通过，将结果返回前端
		if(!"验证通过".equals(checkVcResult)){
			return ResultData.warning(checkVcResult);
		}

		//清除session中的数据
		request.getSession().removeAttribute(SMS.PHONE);
		request.getSession().removeAttribute(SMS.VC_TYPE_UPDATE);

		//更新数据库中的数据,如果更新成功，修改session中的数据
		if(studentDao.updateById(Student.builder().sId(student.getSId()).sPhone(phone).build())>=1){
			student.setSPhone(phone);
			request.getSession().setAttribute("student",student);
			return ResultData.success("修改成功");
		}

		return ResultData.warning("修改失败，请重试");
	}
}
