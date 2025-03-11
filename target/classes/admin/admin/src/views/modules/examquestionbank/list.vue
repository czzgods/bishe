<template>
	<div class="main-content" :style='{"width":"100%","padding":"20px 40px","fontSize":"15px"}'>
		<!-- 列表页 -->
		<template v-if="!showFlag">
			<el-form :style='{"border":"0px solid #fff","margin":"0 0px 20px","flexWrap":"wrap","background":"none","display":"flex","width":"100%","justifyContent":"space-between"}' :inline="true" :model="searchForm" class="center-form-pv">
				<el-row :style='{"padding":"0px","alignItems":"center","flexWrap":"wrap","background":"none","display":"flex"}'>
					<div :style='{"alignItems":"center","margin":"0 10px 0 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">试题</label>
						<el-input v-model="searchForm.questionname" placeholder="试题名称" clearable></el-input>
					</div>
					<el-button class="search" :style='{"border":"0","cursor":"pointer","padding":"0 15px 0 10px","color":"#fff","borderRadius":"20px","background":"#4dc2de","width":"auto","fontSize":"16px","height":"34px"}' type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing02" :style='{"margin":"0 0px","fontSize":"16px","color":"#fff","height":"40px"}'></span>
						查询
					</el-button>
				</el-row>
				<el-row class="actions" :style='{"padding":"0px","margin":"0px 0","flexWrap":"wrap","background":"none","display":"flex"}'>
					<el-button class="add" :style='{"border":"0px solid #ddd","cursor":"pointer","padding":"0 15px","margin":"4px","color":"#fff","borderRadius":"20px","background":"#4dc2de","width":"auto","fontSize":"inherit","height":"34px"}' type="success" @click="addOrUpdateHandler()">
						<span class="icon iconfont icon-tianjia13" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","height":"34px"}'></span>
						添加
					</el-button>
					<el-button class="del" :disabled="dataListSelections.length <= 0" :style='{"border":"0px solid #ddd","cursor":"pointer","padding":"0 15px","margin":"4px","color":"#fff","borderRadius":"20px","background":"#be5353","width":"auto","fontSize":"inherit","height":"34px"}' type="danger" @click="deleteHandler()">
						<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","height":"34px"}'></span>
						删除
					</el-button>
					<download-excel v-if="isAuth('examquestion','导出')" class = "export-excel-wrapper" :data = "dataList" :fields = "json_fields" name = "试卷题目.xls">
						<!-- 导出excel -->
						<el-button class="btn18" type="success">
							<span class="icon iconfont icon-daochu4" :style='{"margin":"0 2px","fontSize":"14px","color":"#939393","height":"34px"}'></span>
							导出
						</el-button>
					</download-excel>
					<el-button class="btn18" v-if="isAuth('examquestion','打印')"  type="success" @click="printJson">
						<span class="icon iconfont icon-dayin2" :style='{"margin":"0 2px","fontSize":"14px","color":"#939393","height":"34px"}'></span>
						打印
					</el-button>
				</el-row>
			</el-form>
	
			<div :style='{"border":"0px solid #fff","padding":"0","color":"#000","borderRadius":"10px","background":"#fff","borderWidth":"0px","width":"100%","fontSize":"14px"}'>
				<el-table
					:stripe='false'
					:style='{"padding":"0px 0","borderColor":"#f6f6f6","borderRadius":"0","borderWidth":"0px 0 0 0px","background":"#fff","width":"100%","fontSize":"inherit","borderStyle":"solid"}'
					:data="dataList"
					:border='true'
					v-loading="dataListLoading"
					@selection-change="selectionChangeHandler"
					style="width: 100%;"
				>
					<el-table-column :resizable='true' type="selection" header-align="center" align="center" width="50"></el-table-column>
					<el-table-column
						:resizable='true' :sortable='true'
						width="300"
						prop="questionname"
						header-align="center"
						align="center"
						sortable
						label="试题名称"
					>
						<template slot-scope="scope">
							<div v-html="scope.row.questionname"></div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="score" header-align="center" align="center" sortable label="分值"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="answer" header-align="center" align="center" sortable label="答案"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="type" header-align="center" align="center" sortable label="类型">
						<template slot-scope="scope">
							<el-tag v-if="scope.row.type==0">单选题</el-tag>
							<el-tag v-if="scope.row.type==1" type="info">多选题</el-tag>
							<el-tag v-if="scope.row.type==2" type="success">判断题</el-tag>
							<el-tag v-if="scope.row.type==3" type="warning">填空题</el-tag>
							<el-tag v-if="scope.row.type==4" type="warning">主观题</el-tag>
						</template>
					</el-table-column>
					<el-table-column
						header-align="center"
						align="center"
						width="150"
						label="操作"
					>
						<template slot-scope="scope">
							<el-button class="edit" :style='{"border":"0px solid #157ed2","cursor":"pointer","padding":"0 15px","margin":"0 5px 5px 0","color":"#1fc3cb","borderRadius":"4px","background":"url(http://codegen.caihongy.cn/20241006/ff34117d34d84517ad3d0a6a7bc7cb41.png) no-repeat center center / 100% 100%","width":"auto","fontSize":"14px","minWidth":"60px","height":"36px"}' type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xiugai17" :style='{"margin":"0 0px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								修改
							</el-button>
							<el-button class="del" :style='{"border":"0px solid #157ed2","cursor":"pointer","padding":"0 15px","margin":"0 5px 5px 0","color":"#1fc3cb","borderRadius":"4px","background":"url(http://codegen.caihongy.cn/20241006/2199ca2de0444272a5e77cb22da4d081.png) no-repeat center center / 100% 100%","width":"auto","fontSize":"14px","minWidth":"60px","height":"36px"}' type="primary" @click="deleteHandler(scope.row.id)">
								<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 0px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								删除
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>

			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:total="totalPage"
				:layout="layouts.join()"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="false"
				:style='{"padding":"10px 20px","boxShadow":"1px 2px 4px #ddd","margin":"20px 0","whiteSpace":"nowrap","color":"#333","textAlign":"left","display":"inline-block","borderRadius":"10px","background":"#fff","width":"auto","fontSize":"inherit","position":"relative","fontWeight":"500"}'
			></el-pagination>
		</template>
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-else :parent="this" ref="addOrUpdate"></add-or-update>
	</div>
</template>
<script>
	import AddOrUpdate from "./question-add-or-update";
	export default {
		data() {
			return {
				layouts: ["total","prev","pager","next","sizes"],
				searchForm: {
					key: ""
				},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: false,
				//导出excel
				json_fields: {
					"题目名称": "questionname",    //常规字段
					"题目类型": {
					field: 'type',
					callback: value => {
						let str = ''
						switch (value) {
							case 0:
								str = '单选题'
								break
							case 1:
								str = '多选题'
								break
							case 2:
								str = '判断题'
								break
							case 3:
								str = '填空题'
								break
							case 4:
								str = '主观题'
								break
							}
							return str
						}
                    },
					"选项": "options",    //常规字段
					"分值": "score",    //常规字段
					"正确答案": "answer",    //常规字段
					"答案解析": "analysis",    //常规字段
				},
				json_meta: [
					[
						{
						  "key": "charset",
						  "value": "utf- 8"
						}
					]
				]
			};
		},
		mounted() {
			this.init();
			this.getDataList();
		},
		components: {
			AddOrUpdate
		},
		methods: {
			init() {},
			search() {
				this.pageIndex = 1;
				this.getDataList();
			},
			// 获取数据列表
			getDataList() {
				this.dataListLoading = true;
				var params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: "id"
				};
				if(this.searchForm.questionname){
					params['questionname'] = `%${this.searchForm.questionname}%`
				}
				this.$http({
					url: this.$api.examquestionbankpage,
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						for(let x in data.data.list){
							data.data.list[x].questionname = data.data.list[x].questionname.replace(/img src/gi,"img style=\"width:100%;\" src");
						}
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 多选
			selectionChangeHandler(val) {
				this.dataListSelections = val;
			},
			// 添加/修改
			addOrUpdateHandler(id) {
				this.showFlag = true;
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(id);
				});
			},
			// 删除
			deleteHandler(id) {
				var ids = id
					? [Number(id)]
					: this.dataListSelections.map(item => {
						return Number(item.id);
					});
				this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(() => {
					this.$http({
						url: this.$api.examquestionbankdelete,
						method: "post",
						data: ids
					}).then(({ data }) => {
						if (data && data.code === 0) {
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.search();
								}
							});
						} else {
							this.$message.error(data.msg);
						}
					});
				});
			},
			async printJson() {
				//通过getdata调用后台接口获取数据封装到res
				this.list = this.dataList;
				let data = []
				for (let i=0; i < this.list.length; i++) {
					let typeName = '';
					if(this.list[i].type==0) {
						typeName = '单选题'
					} else if(this.list[i].type==1) {
						typeName = '多选题'
					} else if(this.list[i].type==2) {
						typeName = '判断题'
					} else if(this.list[i].type==3) {
						typeName = '填空题'
					} else if(this.list[i].type==4) {
						typeName = '主观题'
					}
					data.push({
						questionname: this.list[i].questionname,
						type: typeName,
						options: this.list[i].options,
						score: this.list[i].score,
						answer: this.list[i].answer,
						analysis: this.list[i].analysis,
					})
				}
				printJS({
					printable: data,
					properties: [
						{
							field: 'questionname',
							displayName: '题目名称',
							columnSize: 1
						},
						{
							field: 'type',
							displayName: '题目类型',
							columnSize: 1
						},
						{
							field: 'options',
							displayName: '选项',
							columnSize: 1
						},
						{
							field: 'score',
							displayName: '分值',
							columnSize: 1
						},
						{
							field: 'answer',
							displayName: '正确答案',
							columnSize: 1
						},
						{
							field: 'analysis',
							displayName: '答案解析',
							columnSize: 1
						},
					],
					type: 'json',
					header: '试卷题目',
					// 样式设置
					gridStyle: 'border: 2px solid #3971A5;',
					gridHeaderStyle: 'color: red;  border: 2px solid #3971A5;'
				})
			},
		}
	};
</script>
<style lang="scss" scoped>
    //导出excel
      .export-excel-wrapper{
        display: inline-block;
      }
	.form-content {
		background: transparent;
	}
	.table-content {
		background: transparent;
	}
	
	
	// form
	.center-form-pv .el-input {
				width: auto;
			}
	.center-form-pv .el-input /deep/ .el-input__inner {
				border: 1px solid #ddd;
				border-radius: 20px;
				padding: 0 12px;
				color: #666;
				width: 150px;
				font-size: 15px;
				height: 34px;
			}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
				color: #999;
				background: #4dc2de;
				font-weight: 500;
				width: 100%;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
				background: none;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
				padding: 8px 0;
				background: none;
				border-color: #e3e8e9;
				border-width: 0 4px 0px 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
				padding: 0 0 0 5px;
				word-wrap: normal;
				color: #fff;
				white-space: normal;
				font-weight: bold;
				display: flex;
				vertical-align: middle;
				font-size: 14px;
				line-height: 24px;
				text-overflow: ellipsis;
				word-break: break-all;
				width: 100%;
				align-items: center;
				position: relative;
				min-width: 110px;
			}
	
	
	.el-table /deep/ .el-table__body-wrapper tbody {
				width: 100%;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr {
				background: #fff;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 4px 0;
				color: #333;
				background: #fff;
				font-size: inherit;
				border-color: #e3e8e9;
				border-width: 0 4px 0px 0;
				border-style: solid;
				text-align: left;
			}
	
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
				padding: 4px 0;
				color: #333;
				background: #fcfcfc;
				border-color: #e3e8e9;
				border-width: 0 4px 0px 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 4px 0;
				color: #333;
				background: #fff;
				font-size: inherit;
				border-color: #e3e8e9;
				border-width: 0 4px 0px 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
				padding: 0 0 0 5px;
				overflow: hidden;
				word-break: break-all;
				white-space: normal;
				font-size: inherit;
				line-height: 24px;
				text-overflow: ellipsis;
			}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
				margin: 0 10px 0 0;
				color: #666;
				font-weight: 400;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev {
				border: none;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: auto;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next {
				border: none;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: auto;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #C0C4CC;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: auto;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #C0C4CC;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: auto;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager {
				padding: 0;
				margin: 0;
				display: inline-block;
				vertical-align: top;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number {
				cursor: pointer;
				border-radius: 2px;
				padding: 0 10px;
				margin: 0 2px;
				color: #666;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: 28px;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0 2px;
				color: #fff;
				background: #4dc2de;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: 28px;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
				cursor: default;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0 2px;
				color: #fff;
				background: #4dc2de;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: 28px;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
				margin: 0 5px;
				width: 100px;
				position: relative;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
				border: 0px solid #DCDFE6;
				cursor: pointer;
				padding: 0 25px 0 8px;
				color: #606266;
				display: inline-block;
				font-size: 16px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
				top: 0;
				position: absolute;
				right: 0;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
				cursor: pointer;
				color: #C0C4CC;
				width: 25px;
				font-size: 14px;
				line-height: 28px;
				text-align: center;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
				margin: 0 0 0 24px;
				color: #606266;
				display: inline-block;
				vertical-align: top;
				font-size: 16px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
				border-radius: 3px;
				padding: 0 2px;
				margin: 0 2px;
				display: inline-block;
				width: 50px;
				font-size: 14px;
				line-height: 18px;
				position: relative;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 3px;
				color: #606266;
				display: inline-block;
				font-size: 16px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
	
	.center-form-pv .search {
				border: 0;
				cursor: pointer;
				border-radius: 20px;
				padding: 0 15px 0 10px;
				color: #fff;
				background: #4dc2de;
				width: auto;
				font-size: 16px;
				height: 34px;
			}
	
	.center-form-pv .search:hover {
				opacity: 0.8;
			}
	
	.center-form-pv .actions .add {
				border: 0px solid #ddd;
				cursor: pointer;
				border-radius: 20px;
				padding: 0 15px;
				margin: 4px;
				color: #fff;
				background: #4dc2de;
				width: auto;
				font-size: inherit;
				height: 34px;
			}
	
	.center-form-pv .actions .add:hover {
				opacity: 0.8;
			}
	
	.center-form-pv .actions .del {
				border: 0px solid #ddd;
				cursor: pointer;
				border-radius: 20px;
				padding: 0 15px;
				margin: 4px;
				color: #fff;
				background: #be5353;
				width: auto;
				font-size: inherit;
				height: 34px;
			}
	
	.center-form-pv .actions .del:hover {
				opacity: 0.8;
			}
	
	.center-form-pv .actions .btn18 {
				border: 0px solid #939393;
				cursor: pointer;
				border-radius: 20px;
				padding: 0 15px;
				margin: 4px;
				color: #666;
				background: #fff;
				width: auto;
				font-size: inherit;
				height: 34px;
			}
	
	.center-form-pv .actions .btn18:hover {
				opacity: 0.8;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
				border: 0px solid #157ed2;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 15px;
				margin: 0 5px 5px 0;
				color: #1fc3cb;
				background: url(http://codegen.caihongy.cn/20241006/ff34117d34d84517ad3d0a6a7bc7cb41.png) no-repeat center center / 100% 100%;
				width: auto;
				font-size: 14px;
				min-width: 60px;
				height: 36px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
				opacity: 0.8;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
				border: 0px solid #157ed2;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 15px;
				margin: 0 5px 5px 0;
				color: #1fc3cb;
				background: url(http://codegen.caihongy.cn/20241006/2199ca2de0444272a5e77cb22da4d081.png) no-repeat center center / 100% 100%;
				width: auto;
				font-size: 14px;
				min-width: 60px;
				height: 36px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
				opacity: 0.8;
			}
	
	.one .list1-edit {
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 15px;
				margin: 0 5px 5px 0;
				outline: none;
				color: #fff;
				background: #409eff;
				width: auto;
				font-size: 14px;
				height: 32px;
			}
	
	.one .list1-edit:hover {
				opacity: 0.8;
			}
	
	.one .list1-del {
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 15px;
				margin: 0 5px 5px 0;
				outline: none;
				color: #fff;
				background: rgba(255, 0, 0, 1);
				width: auto;
				font-size: 14px;
				height: 32px;
			}
	
	.one .list1-del:hover {
				opacity: 0.8;
			}
</style>
