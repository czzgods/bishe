import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import ExamPaper from '../pages/exam/examPaper'
import Exam from '../pages/exam/exam'
import ExamList from '../pages/exam/examList'
import ExamRecord from '../pages/exam/examRecord'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import jiazhangList from '../pages/jiazhang/list'
import jiazhangDetail from '../pages/jiazhang/detail'
import jiazhangAdd from '../pages/jiazhang/add'
import xueshengList from '../pages/xuesheng/list'
import xueshengDetail from '../pages/xuesheng/detail'
import xueshengAdd from '../pages/xuesheng/add'
import bianchengjiaoshiList from '../pages/bianchengjiaoshi/list'
import bianchengjiaoshiDetail from '../pages/bianchengjiaoshi/detail'
import bianchengjiaoshiAdd from '../pages/bianchengjiaoshi/add'
import kechengxinxiList from '../pages/kechengxinxi/list'
import kechengxinxiDetail from '../pages/kechengxinxi/detail'
import kechengxinxiAdd from '../pages/kechengxinxi/add'
import kechengbaomingList from '../pages/kechengbaoming/list'
import kechengbaomingDetail from '../pages/kechengbaoming/detail'
import kechengbaomingAdd from '../pages/kechengbaoming/add'
import kechengfenleiList from '../pages/kechengfenlei/list'
import kechengfenleiDetail from '../pages/kechengfenlei/detail'
import kechengfenleiAdd from '../pages/kechengfenlei/add'
import xuexiziliaoList from '../pages/xuexiziliao/list'
import xuexiziliaoDetail from '../pages/xuexiziliao/detail'
import xuexiziliaoAdd from '../pages/xuexiziliao/add'
import paikexinxiList from '../pages/paikexinxi/list'
import paikexinxiDetail from '../pages/paikexinxi/detail'
import paikexinxiAdd from '../pages/paikexinxi/add'
import xuexijinzhanList from '../pages/xuexijinzhan/list'
import xuexijinzhanDetail from '../pages/xuexijinzhan/detail'
import xuexijinzhanAdd from '../pages/xuexijinzhan/add'
import chatmessageList from '../pages/chatmessage/list'
import chatmessageDetail from '../pages/chatmessage/detail'
import chatmessageAdd from '../pages/chatmessage/add'
import friendList from '../pages/friend/list'
import friendDetail from '../pages/friend/detail'
import friendAdd from '../pages/friend/add'
import syslogList from '../pages/syslog/list'
import syslogDetail from '../pages/syslog/detail'
import syslogAdd from '../pages/syslog/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import aboutusList from '../pages/aboutus/list'
import aboutusDetail from '../pages/aboutus/detail'
import aboutusAdd from '../pages/aboutus/add'
import systemintroList from '../pages/systemintro/list'
import systemintroDetail from '../pages/systemintro/detail'
import systemintroAdd from '../pages/systemintro/add'
import discussbianchengjiaoshiList from '../pages/discussbianchengjiaoshi/list'
import discussbianchengjiaoshiDetail from '../pages/discussbianchengjiaoshi/detail'
import discussbianchengjiaoshiAdd from '../pages/discussbianchengjiaoshi/add'
import discusskechengxinxiList from '../pages/discusskechengxinxi/list'
import discusskechengxinxiDetail from '../pages/discusskechengxinxi/detail'
import discusskechengxinxiAdd from '../pages/discusskechengxinxi/add'
import discussxuexiziliaoList from '../pages/discussxuexiziliao/list'
import discussxuexiziliaoDetail from '../pages/discussxuexiziliao/detail'
import discussxuexiziliaoAdd from '../pages/discussxuexiziliao/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'examPaper',
					component: ExamPaper
				},
				{
					path: 'examList',
					component:ExamList
				},
				{
					path: 'examRecord/:type',
					component:ExamRecord
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'jiazhang',
					component: jiazhangList
				},
				{
					path: 'jiazhangDetail',
					component: jiazhangDetail
				},
				{
					path: 'jiazhangAdd',
					component: jiazhangAdd
				},
				{
					path: 'xuesheng',
					component: xueshengList
				},
				{
					path: 'xueshengDetail',
					component: xueshengDetail
				},
				{
					path: 'xueshengAdd',
					component: xueshengAdd
				},
				{
					path: 'bianchengjiaoshi',
					component: bianchengjiaoshiList
				},
				{
					path: 'bianchengjiaoshiDetail',
					component: bianchengjiaoshiDetail
				},
				{
					path: 'bianchengjiaoshiAdd',
					component: bianchengjiaoshiAdd
				},
				{
					path: 'kechengxinxi',
					component: kechengxinxiList
				},
				{
					path: 'kechengxinxiDetail',
					component: kechengxinxiDetail
				},
				{
					path: 'kechengxinxiAdd',
					component: kechengxinxiAdd
				},
				{
					path: 'kechengbaoming',
					component: kechengbaomingList
				},
				{
					path: 'kechengbaomingDetail',
					component: kechengbaomingDetail
				},
				{
					path: 'kechengbaomingAdd',
					component: kechengbaomingAdd
				},
				{
					path: 'kechengfenlei',
					component: kechengfenleiList
				},
				{
					path: 'kechengfenleiDetail',
					component: kechengfenleiDetail
				},
				{
					path: 'kechengfenleiAdd',
					component: kechengfenleiAdd
				},
				{
					path: 'xuexiziliao',
					component: xuexiziliaoList
				},
				{
					path: 'xuexiziliaoDetail',
					component: xuexiziliaoDetail
				},
				{
					path: 'xuexiziliaoAdd',
					component: xuexiziliaoAdd
				},
				{
					path: 'paikexinxi',
					component: paikexinxiList
				},
				{
					path: 'paikexinxiDetail',
					component: paikexinxiDetail
				},
				{
					path: 'paikexinxiAdd',
					component: paikexinxiAdd
				},
				{
					path: 'xuexijinzhan',
					component: xuexijinzhanList
				},
				{
					path: 'xuexijinzhanDetail',
					component: xuexijinzhanDetail
				},
				{
					path: 'xuexijinzhanAdd',
					component: xuexijinzhanAdd
				},
				{
					path: 'chatmessage',
					component: chatmessageList
				},
				{
					path: 'chatmessageDetail',
					component: chatmessageDetail
				},
				{
					path: 'chatmessageAdd',
					component: chatmessageAdd
				},
				{
					path: 'friend',
					component: friendList
				},
				{
					path: 'friendDetail',
					component: friendDetail
				},
				{
					path: 'friendAdd',
					component: friendAdd
				},
				{
					path: 'syslog',
					component: syslogList
				},
				{
					path: 'syslogDetail',
					component: syslogDetail
				},
				{
					path: 'syslogAdd',
					component: syslogAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'aboutus',
					component: aboutusList
				},
				{
					path: 'aboutusDetail',
					component: aboutusDetail
				},
				{
					path: 'aboutusAdd',
					component: aboutusAdd
				},
				{
					path: 'systemintro',
					component: systemintroList
				},
				{
					path: 'systemintroDetail',
					component: systemintroDetail
				},
				{
					path: 'systemintroAdd',
					component: systemintroAdd
				},
				{
					path: 'discussbianchengjiaoshi',
					component: discussbianchengjiaoshiList
				},
				{
					path: 'discussbianchengjiaoshiDetail',
					component: discussbianchengjiaoshiDetail
				},
				{
					path: 'discussbianchengjiaoshiAdd',
					component: discussbianchengjiaoshiAdd
				},
				{
					path: 'discusskechengxinxi',
					component: discusskechengxinxiList
				},
				{
					path: 'discusskechengxinxiDetail',
					component: discusskechengxinxiDetail
				},
				{
					path: 'discusskechengxinxiAdd',
					component: discusskechengxinxiAdd
				},
				{
					path: 'discussxuexiziliao',
					component: discussxuexiziliaoList
				},
				{
					path: 'discussxuexiziliaoDetail',
					component: discussxuexiziliaoDetail
				},
				{
					path: 'discussxuexiziliaoAdd',
					component: discussxuexiziliaoAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
		{
			path: '/exam',
			component: Exam
		}
	]
})
