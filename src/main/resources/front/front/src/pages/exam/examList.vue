<template>
	<div :style='{"width":"100%","padding":"20px 16%","margin":"0px auto","position":"relative","background":"#F2F3F7"}'>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="section-title" :style='{"margin":"0","color":"#fff","textAlign":"center","background":"url(http://codegen.caihongy.cn/20241119/91b3da33f957476e8c833cb4ebc67d27.png) center center/cover no-repeat ","width":"100%","fontSize":"30px","lineHeight":"160px","height":"240px"}'>测试</div>
		<el-table :data="tableData" style="width: 100%">
			<el-table-column label="编程试卷名称" prop="papername"> </el-table-column>
			<el-table-column label="测试得分">
				<template slot-scope="scope">
					<el-tag v-if="scope.row.myscore==0&&scope.row.ismark==0" type="danger">{{scope.row.myscore}}</el-tag>
					<el-tag v-else-if="scope.row.myscore>0&&scope.row.ismark==0" type="success">{{scope.row.myscore}}</el-tag>
					<el-tag v-else-if="scope.row.ismark>0" type="warning">批阅中</el-tag>
				</template>
			</el-table-column>
			<el-table-column label="准确率">
				<template slot-scope="scope">
					<el-tag type="success" v-if="scope.row.ismark==0">{{(scope.row.accuracy * 100).toFixed(0)}}%</el-tag>
					<el-tag type="warning" v-if="scope.row.ismark>0">/</el-tag>
				</template>
			</el-table-column>
			<el-table-column label="错误率">
				<template slot-scope="scope">
					<el-tag type="danger" v-if="scope.row.ismark==0">{{((1 - Number(scope.row.accuracy)) * 100).toFixed(0)}}%</el-tag>
					<el-tag type="warning" v-if="scope.row.ismark>0">/</el-tag>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template slot-scope="scope">
					<el-button type="danger" size="mini" @click="handleView(scope.$index, scope.row)">查看</el-button>
					<el-button v-if="isAuth('examrecord','批卷')&&scope.row.ismark>0" type="primary" size="mini" @click="gradeClick(scope.row)">
						批卷
					</el-button>
				</template>
			</el-table-column>
		</el-table>
	
		<el-pagination
			background
			id="pagination" class="pagination"
			:pager-count="7"
			:page-size="pageSize"
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
		<el-dialog title="批卷" :visible.sync="gradeVisible" fullscreen>
			<el-card v-for="(item,index) in gradeList" :key="index" style="width: 90%;margin: 10px auto">
				<div style="padding: 20px;box-sizing:border-box;border-bottom:1px solid #eee">
					{{index + 1}}、<span v-html="item.questionname"></span> （ {{item.score}} ） 					<el-tag type="success" v-if="item.type==0">单选题</el-tag>
					<el-tag type="warning" v-if="item.type==1">多选题</el-tag>
					<el-tag type="info" v-if="item.type==2">判断题</el-tag>
					<el-tag type="primary" v-if="item.type==3">填空题</el-tag>
					<el-tag type="danger" v-if="item.type==4">主观题</el-tag>
				</div>
				<div style="padding: 10px;box-sizing:border-box">
					考生答案：{{item.myanswer}}
				</div>
				<div style="padding: 10px;box-sizing:border-box" v-if="item.type!=4">
					正确答案：{{item.answer}}
				</div>
				<div style="padding: 20px;box-sizing:border-box">
					解析：{{item.analysis}}
				</div>
				<div style="padding: 20px;box-sizing:border-box;display:flex;align-items:center" v-if="item.type==4">
					评分：<el-input-number v-model="item.myscore" :min="0" :max="item.score"></el-input-number>
				</div>
			</el-card>
			<span slot="footer" class="dialog-footer">
				<el-button @click="gradeVisible=false">取 消</el-button>
				<el-button type="primary" @click="gradeSave">确 定</el-button>
			</span>
		</el-dialog>
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
				totalPage: 1,
				gradeList: [],
				gradeVisible: false
			}
		},
		created() {
			this.getExamList(1);
		},
		methods: {
			backClick() {
				this.$router.push('/index/center')
			},
			getExamList(page) {
				this.$http.get('examrecord/groupby', {params: {page, limit: this.pageSize, userid: localStorage.getItem('frontUserid')}}).then(res => {
					if (res.data.code == 0) {
						this.tableData = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
					}
				});
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
			handleView(index, row) {
				this.$router.push({path: '/index/examRecord/1', query: {paperid: row.paperid}})
			},
			// 批卷
			gradeClick(row) {
				this.$http.get('examrecord/page',{params:{page:1,limit:100,paperid:row.paperid,userid:row.userid}}).then(res=>{
					if (res.data.code == 0) {
						for(let x in res.data.data.list){
							if(res.data.data.list[x].type==4){
								res.data.data.list[x].myscore = 0
							}
							res.data.data.list[x].questionname = res.data.data.list[x].questionname.replace(/img src/gi,"img style=\"width:100%;\" src");
						}
						this.gradeList = res.data.data.list
						this.gradeVisible = true
					}
				})
			},
			gradeSave(){
				for(let i in this.gradeList){
					this.updaterecord(this.gradeList[i])
				}
				this.$message({
					message: "批卷成功",
					type: "success",
					duration: 1500,
					onClose: () => {
						this.getDataList()
						this.gradeVisible = false
					}
				});
			},
			updaterecord(item) {
				item.ismark = 1
				this.$http.post('examrecord/update',item).then(res=>{})
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.section {
		width: 900px;
		margin: 0 auto;
	}

</style>
