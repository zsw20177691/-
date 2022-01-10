import axios from 'axios'
import Vue from "vue";
import common from '../common/common.js'

axios.defaults.withCredentials = true
axios.defaults.baseURL = common.baseUrl; // 关键步骤–填写后台请求统一的地址
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

// 请求拦截
axios.interceptors.request.use(function (config) {
    return config;
  }, function (error) {
    return Promise.reject(error);
  });

// 响应拦截
axios.interceptors.response.use(function (response) {
    return response;
  }, function (error) {

    if (error.response.data.msg == null) {
      Vue.prototype.$message("执行操作异常联系管理员");
    } else{
      Vue.prototype.$message(error.response.data.msg);
    }

    return Promise.reject(error);
  });


var analysis = {transformRequest: [function(data) {let params = '';for (let index in data) {params += index + '=' + data[index] + '&';}return params;}]}

// 登录
export const login= (params) => axios.post('user/login',params,analysis)

// 注册时，简称用户名是否存在
export const checkUsername= (param) => axios.get('user/checkUsername',{params: param})

// 查询岗位字典数据
export const queryTreePosition= () => axios.get('tree/position')

// 查询行业字典数据
export const queryTreeIndustry= () => axios.get('tree/industry')

// 查询地区字典数据
export const queryTreeArea= () => axios.get('tree/area')

// 注册普通用户
export const registerPersonal= (params) => axios.post('user/registerPersonal',params,analysis)

// 注册公司
export const registerCompany= (params) => axios.post('user/registerCompany',params,analysis)

// 企业新增招聘
export const addRecruit= (params) => axios.post('recruit/add',params,analysis)

// 企业查询公司已发布的招聘
export const getRecruit= (param) => axios.get('recruit/list',{params: param})

// 修改招聘信息
export const editRecruit= (param) => axios.put('recruit/edit',param)

// 获取当前用户信息
export const getUserInfo= (param) => axios.get('user/info')
//获取用户的就业情况
export const getWorkInfo= (param) => axios.get('studentworkinfo/workInfo',{params: param})
// 查询指定学生信息
export const getPersonalInfo= (id) => axios.get('user/personal/'+id)

// 查询指定公司信息
export const getCompanyInfo= (id) => axios.get('user/company/'+id)

// 修改用户名、 头像
export const editUser= (params) => axios.post('user/edit',params,analysis)

// 修改密码
export const editUserPassword= (params) => axios.post('user/editPassword',params,analysis)

// 编辑企业信息
export const editCompany= (param) => axios.put('user/editCompany',param)

// 编辑普通用户信息
export const editPersonal= (param) => axios.put('user/editPersonal',param)

// 主页查询所有公司发布的职位数据
export const getRecruitInfoList= (param) => axios.get('recruit/infoList',{params: param})

// 查询某一具体岗位信息
export const getRecruitInfo= (id) => axios.get('recruit/info/'+id)

// 投递简历
export const addDelivery= (param) => axios.post('delivery/add',param)

// 查询自己投递的岗位
export const getDelivery= (param) => axios.get('delivery/list/',{params: param})

// 查询投递本公司的求职
export const getDeliveryByCompany= (param) => axios.get('delivery/company/',{params: param})

// 查询自己投递的岗位
export const editDelivery= (param) => axios.put('delivery/edit',param)

// 查询用户列表
export const getUsers= (param) => axios.get('user/list',{params: param})

export const editUserStatus= (param) => axios.put('user/editStatus',param)
//保存毕业生工作情况
export const presservation= (param) => axios.post('studentworkinfo/editStudentworkInfo',param,analysis)
//毕业生修改个人的就业信息
export const editpresservation= (param) => axios.put('studentworkinfo/edit',param)


