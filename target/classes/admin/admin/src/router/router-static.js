import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import adminexam from '@/views/modules/exampaperlist/exam'
	import news from '@/views/modules/news/list'
	import aboutus from '@/views/modules/aboutus/list'
	import examfailrecord from '@/views/modules/examfailrecord/list'
	import xuesheng from '@/views/modules/xuesheng/list'
	import examquestion from '@/views/modules/examquestion/list'
	import discussbianchengjiaoshi from '@/views/modules/discussbianchengjiaoshi/list'
	import discussxuexiziliao from '@/views/modules/discussxuexiziliao/list'
	import syslog from '@/views/modules/syslog/list'
	import bianchengjiaoshi from '@/views/modules/bianchengjiaoshi/list'
	import exampaper from '@/views/modules/exampaper/list'
	import discusskechengxinxi from '@/views/modules/discusskechengxinxi/list'
	import kechengxinxi from '@/views/modules/kechengxinxi/list'
	import xuexijinzhan from '@/views/modules/xuexijinzhan/list'
	import jiazhang from '@/views/modules/jiazhang/list'
	import kechengbaoming from '@/views/modules/kechengbaoming/list'
	import systemintro from '@/views/modules/systemintro/list'
	import kechengfenlei from '@/views/modules/kechengfenlei/list'
	import paikexinxi from '@/views/modules/paikexinxi/list'
	import xuexiziliao from '@/views/modules/xuexiziliao/list'
	import config from '@/views/modules/config/list'
	import examrecord from '@/views/modules/examrecord/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '公告资讯',
		component: news
	}
	,{
		path: '/aboutus',
		name: '关于我们',
		component: aboutus
	}
	,{
		path: '/examfailrecord',
		name: '错题本',
		component: examfailrecord
	}
	,{
		path: '/xuesheng',
		name: '学生',
		component: xuesheng
	}
	,{
		path: '/examquestion',
		name: '试题管理',
		component: examquestion
	}
	,{
		path: '/discussbianchengjiaoshi',
		name: '编程教师评论',
		component: discussbianchengjiaoshi
	}
	,{
		path: '/discussxuexiziliao',
		name: '学习资料评论',
		component: discussxuexiziliao
	}
	,{
		path: '/syslog',
		name: '系统日志',
		component: syslog
	}
	,{
		path: '/bianchengjiaoshi',
		name: '编程教师',
		component: bianchengjiaoshi
	}
	,{
		path: '/exampaper',
		name: '编程试卷管理',
		component: exampaper
	}
	,{
		path: '/discusskechengxinxi',
		name: '课程信息评论',
		component: discusskechengxinxi
	}
	,{
		path: '/kechengxinxi',
		name: '课程信息',
		component: kechengxinxi
	}
	,{
		path: '/xuexijinzhan',
		name: '学习进展',
		component: xuexijinzhan
	}
	,{
		path: '/jiazhang',
		name: '家长',
		component: jiazhang
	}
	,{
		path: '/kechengbaoming',
		name: '课程报名',
		component: kechengbaoming
	}
	,{
		path: '/systemintro',
		name: '系统简介',
		component: systemintro
	}
	,{
		path: '/kechengfenlei',
		name: '课程分类',
		component: kechengfenlei
	}
	,{
		path: '/paikexinxi',
		name: '排课信息',
		component: paikexinxi
	}
	,{
		path: '/xuexiziliao',
		name: '学习资料',
		component: xuexiziliao
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/examrecord',
		name: '测试记录',
		component: examrecord
	}
	,{
		path: '/newstype',
		name: '公告资讯分类',
		component: newstype
	}
	]
	},
	{
		path: '/adminexam',
		name: 'adminexam',
		component: adminexam,
		meta: {icon:'', title:'adminexam'}
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
