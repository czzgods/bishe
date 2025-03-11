<template>
	<div :style='{"width":"100%","padding":"20px 16%","margin":"0px auto","position":"relative","background":"#F2F3F7"}'>
		<div class="section-title" :style='{"margin":"0","color":"#fff","textAlign":"center","background":"url(http://codegen.caihongy.cn/20241119/91b3da33f957476e8c833cb4ebc67d27.png) center center/cover no-repeat ","width":"100%","fontSize":"30px","lineHeight":"160px","height":"240px"}'>编程试卷</div>
		<el-form :style='{"padding":"20px 10px 10px","boxShadow":"0 4px 8px rgba(0,0,0,.3)","margin":"-10px auto 0","alignItems":"center","borderRadius":"10px","flexWrap":"wrap","background":"#fff","display":"flex","width":"68%","height":"auto"}' :inline="true" :model="formSearch" class="center-form-pv">
			<el-form-item :style='{"width":"auto","margin":"0 10px 10px"}'>
				<div class="lable" v-if="true" :style='{"padding":"0 0px","whiteSpace":"nowrap","textAlign":"right","display":"inline-block","width":"auto","lineHeight":"42px","fontSize":"13px"}'>编程试卷名称：</div>
				<el-input v-model="formSearch.name" placeholder="编程试卷名称" @keydown.enter.native="getExamList(1)" clearable></el-input>
			</el-form-item>
			<el-button :style='{"cursor":"pointer","border":"0","padding":"0px 25px","margin":"0 10px 10px 0","color":"#fff","outline":"none","borderRadius":"4px","background":"#0AB8C1","width":"auto","fontSize":"15px","lineHeight":"42px","fontWeight":"bold","height":"42px"}' type="primary" @click="getExamList(1)"><i v-if="true" :style='{"margin":"0 10px 0 0","fontSize":"14px","color":"#fff","display":"none"}' class="el-icon-search"></i>查询</el-button>
		</el-form>
		<el-table
			:data="tableData"
			style="width: 100%">
			<el-table-column
				label="编程试卷名称"
				prop="name">
			</el-table-column>
			<el-table-column
				label="测试时长">
				<template slot-scope="scope">
					{{ scope.row.time }}分钟
				</template>
			</el-table-column>
			<el-table-column
				label="创建时间"
				prop="addtime">
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template slot-scope="scope">
					<el-button
					type="success"
					size="mini"
					@click="handleExam(scope.$index, scope.row)">测试</el-button>
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
			@size-change="sizeChange"
			@next-click="nextClick"
			></el-pagination>
		
	</div>
</template>

<script>
	export default {
		data() {
			return {
				layouts: '',
				tableData: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				formSearch: {}
			}
		},
		created() {
			this.getExamList(1);
		},
		methods: {
			getExamList(page) {
				let params = {page, limit: this.pageSize, status: 1,sort: 'addtime',order: 'desc'}
				if(this.formSearch.name){
					params['name'] = `%${this.formSearch.name}%`
				}
				this.$http.get('exampaper/list', {params: params}).then(res => {
					if (res.data.code == 0) {
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
		  sizeChange(size){
			this.pageSize = size
			this.getList(1);
		  },
			curChange(page) {
				this.getExamList(page);
			},
			prevClick(page) {
				this.getExamList(page);
			},
			nextClick(page) {
				this.getExamList(page);
			},
			handleExam(index, row) {
				this.$router.push({path: '/exam', query: {id: row.id, time: row.time}})
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.section {
		width: 900px;
		margin: 0 auto;
	}
	.center-form-pv .el-input {
		width: auto;
	}

  
</style>
