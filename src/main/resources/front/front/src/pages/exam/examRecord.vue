<template>
	<div :style='{"width":"100%","padding":"20px 16%","margin":"0px auto","position":"relative","background":"#F2F3F7"}'>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="section-title" :style='{"margin":"0","color":"#fff","textAlign":"center","background":"url(http://codegen.caihongy.cn/20241119/91b3da33f957476e8c833cb4ebc67d27.png) center center/cover no-repeat ","width":"100%","fontSize":"30px","lineHeight":"160px","height":"240px"}'>{{title}}</div>
		<el-table
			:data="tableData"
			style="width: 100%">
			<el-table-column
				label="编程试卷名称"
				prop="papername">
			</el-table-column>
			<el-table-column
				label="试题"
				prop="questionname">
				<template slot-scope="scope">
					<div v-html="scope.row.questionname"></div>
				</template>
			</el-table-column>
			<el-table-column
				prop="type"
				label="试题类型"
			>
				<template slot-scope="scope">
					<el-tag type="success" v-if="scope.row.type==0">单选题</el-tag>
					<el-tag type="warning" v-if="scope.row.type==1">多选题</el-tag>
					<el-tag type="info" v-if="scope.row.type==2">判断题</el-tag>
					<el-tag type="primary" v-if="scope.row.type==3">填空题</el-tag>
					<el-tag type="danger" v-if="scope.row.type==4">主观题</el-tag>
				</template>
			</el-table-column>
			<el-table-column
				label="分值">
				<template slot-scope="scope">
					{{ scope.row.score }}分
				</template>
			</el-table-column>
			<el-table-column
				label="正确答案"
				prop="right">
			</el-table-column>
			<el-table-column
				label="考生答案"
				prop="myanswer">
			</el-table-column>
			<el-table-column
				label="测试得分">
				<template slot-scope="scope">
					{{ scope.row.myscore }}分
				</template>
			</el-table-column>
		</el-table>
		<el-pagination
			background
			id="pagination" class="pagination"
			:pager-count="7"
			:page-size="pageSize"
			:page-sizes="pageSizes"
			prev-text="<"
			next-text=">"
			:hide-on-single-page="false"
			:layout='["total","prev","pager","next"].join()'
			:total="total"
			:style='{"padding":"0","margin":"20px auto","whiteSpace":"nowrap","color":"#333","alignItems":"center","background":"none","display":"flex","width":"100%","fontWeight":"500","justifyContent":"center","height":"auto"}'
			@current-change="curChange"
			@prev-click="prevClick"
			@next-click="nextClick"
		></el-pagination>
	
	</div>
</template>

<script>
	export default {
		data() {
			return {
				layouts: '',
				title: '测试记录',
				tableData: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1
			}
		},
		created() {
			this.getExamRecord(1);
		},
		methods: {
			backClick() {
				this.$router.push('/index/center')
			},
			getExamRecord(page) {
				let otherParams = {};
				if (this.$route.params.type == 0) {
					this.title = '错题本';
					otherParams.myscore = 0;
					otherParams.ismark = 1
				}
				this.$http.get('examrecord/list', {params: Object.assign({page, limit: this.pageSize, paperid: this.$route.query.paperid, userid: localStorage.getItem('frontUserid')}, otherParams)}).then(res => {
					if (res.data.code == 0) {
						let arr = []
						for(let x in res.data.data.list){
							res.data.data.list[x].questionname = res.data.data.list[x].questionname.replace(/img src/gi,"img style=\"width:100%;\" src");
							if(res.data.data.list[x].type==0||res.data.data.list[x].type==2){
								arr = JSON.parse(res.data.data.list[x].options)
								for(let i in arr){
									if(res.data.data.list[x].answer == arr[i].code){
										res.data.data.list[x].right = arr[i].text
									}
								}
							}else if(res.data.data.list[x].type==1){
								arr = JSON.parse(res.data.data.list[x].options)
								res.data.data.list[x].answer.split(',').forEach(item=>{
									for(let i in arr){
										if (item == arr[i].code) {
											if (res.data.data.list[x].right) {
												res.data.data.list[x].right += ','
												res.data.data.list[x].right = res.data.data.list[x].right + arr[i].text
											}else{
												res.data.data.list[x].right = arr[i].text
											}
										}
									}
								})
							}else if(res.data.data.list[x].type==3){
								res.data.data.list[x].right = res.data.data.list[x].answer
							}else if(res.data.data.list[x].type==4){
								res.data.data.list[x].right = '略'
							}
						}
						this.tableData = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			curChange(page) {
				this.getExamRecord(page);
			},
			prevClick(page) {
				this.getExamRecord(page);
			},
			nextClick(page) {
				this.getExamRecord(page);
			},
			handleView(index, row) {
				this.$router.push({path: '/index/examRecord', query: {id: row.id}})
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.section {
		width: 900px;
		margin: 0 auto;
	}
</style>
