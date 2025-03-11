export default {
	baseUrl: 'http://localhost:8080/springboot798884g4/',
	name: '/springboot798884g4',
	indexNav: [
		{
			name: '编程教师',
			url: '/index/bianchengjiaoshi',
		},
		{
			name: '课程信息',
			url: '/index/kechengxinxi',
		},
		{
			name: '学习资料',
			url: '/index/xuexiziliao',
		},
		{
			name: '编程试卷',
			url: '/index/examPaper'
		}, 
		{
			name: '公告资讯',
			url: '/index/news'
		},
	],
	cateList: [
		{
			name: '课程信息',
			refTable: 'kechengfenlei',
			refColumn: 'kechengfenlei',
		},
	]
}
