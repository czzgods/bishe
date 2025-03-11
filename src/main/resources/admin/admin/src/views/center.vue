<template>
	<div :style='{"width":"100%","padding":"20px 40px","fontSize":"15px"}'>
		<el-form
			:style='{"padding":"40px 30px","borderColor":"#eee","borderRadius":"10px","borderStyle":"solid","borderWidth":"0px 0 0","background":"#fff"}'
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			label-width="180px"
		>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='jiazhang'"  label="家长账号" prop="jiazhangzhanghao">
					<el-input v-model="ruleForm.jiazhangzhanghao" readonly						placeholder="家长账号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='jiazhang'"  label="家长姓名" prop="jiazhangxingming">
					<el-input v-model="ruleForm.jiazhangxingming" 						placeholder="家长姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='jiazhang'"  label="性别" prop="xingbie">
					<el-select v-model="ruleForm.xingbie"  placeholder="请选择性别">
						<el-option
							v-for="(item,index) in jiazhangxingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='jiazhang'"  label="家长电话" prop="jiazhangdianhua">
					<el-input v-model="ruleForm.jiazhangdianhua" 						placeholder="家长电话" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='jiazhang'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="jiazhangtouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='jiazhang'" label="学生账号" prop="xueshengzhanghao">
					<el-select  @change="jiazhangxueshengzhanghaoChange"  v-model="ruleForm.xueshengzhanghao" placeholder="请选择学生账号">
						<el-option
							v-for="(item,index) in jiazhangxueshengzhanghaoOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='jiazhang'"  label="学生姓名" prop="xueshengxingming">
					<el-input v-model="ruleForm.xueshengxingming" 						placeholder="学生姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='xuesheng'"  label="学生账号" prop="xueshengzhanghao">
					<el-input v-model="ruleForm.xueshengzhanghao" readonly						placeholder="学生账号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='xuesheng'"  label="学生姓名" prop="xueshengxingming">
					<el-input v-model="ruleForm.xueshengxingming" 						placeholder="学生姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='xuesheng'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="xueshengtouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='xuesheng'"  label="性别" prop="xingbie">
					<el-select v-model="ruleForm.xingbie"  placeholder="请选择性别">
						<el-option
							v-for="(item,index) in xueshengxingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='xuesheng'"  label="年龄" prop="nianling">
					<el-input v-model="ruleForm.nianling" 						placeholder="年龄" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='xuesheng'" label="家长账号" prop="jiazhangzhanghao">
					<el-select  @change="xueshengjiazhangzhanghaoChange"  v-model="ruleForm.jiazhangzhanghao" placeholder="请选择家长账号">
						<el-option
							v-for="(item,index) in xueshengjiazhangzhanghaoOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='xuesheng'"  label="家长姓名" prop="jiazhangxingming">
					<el-input v-model="ruleForm.jiazhangxingming" 						placeholder="家长姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='xuesheng'"  label="家长电话" prop="jiazhangdianhua">
					<el-input v-model="ruleForm.jiazhangdianhua" 						placeholder="家长电话" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='xuesheng'"  label="家庭住址" prop="jiatingzhuzhi">
					<el-input v-model="ruleForm.jiatingzhuzhi" 						placeholder="家庭住址" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='bianchengjiaoshi'"  label="教师工号" prop="jiaoshigonghao">
					<el-input v-model="ruleForm.jiaoshigonghao" readonly						placeholder="教师工号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='bianchengjiaoshi'"  label="教师姓名" prop="jiaoshixingming">
					<el-input v-model="ruleForm.jiaoshixingming" 						placeholder="教师姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='bianchengjiaoshi'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="bianchengjiaoshitouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='bianchengjiaoshi'"  label="性别" prop="xingbie">
					<el-select v-model="ruleForm.xingbie"  placeholder="请选择性别">
						<el-option
							v-for="(item,index) in bianchengjiaoshixingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='bianchengjiaoshi'"  label="擅长领域" prop="shanzhanglingyu">
					<el-input v-model="ruleForm.shanzhanglingyu" 						placeholder="擅长领域" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='bianchengjiaoshi'"  label="手机号码" prop="shoujihaoma">
					<el-input v-model="ruleForm.shoujihaoma" 						placeholder="手机号码" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='bianchengjiaoshi'"  label="简介" prop="jianjie">
					<el-input v-model="ruleForm.jianjie" 						placeholder="简介" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='bianchengjiaoshi'" label="教学经验" prop="jiaoxuejingyan">
					<editor
						style="min-width: 200px; max-width: 600px;"
						 :style='{"minHeight":"250px","border":"0px solid #ccc","width":"100%","background":"#fff","height":"auto"}'
						v-model="ruleForm.jiaoxuejingyan" 
						class="editor" 
						action="file/upload">
					</editor>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='users'" label="用户名" prop="username">
					<el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"49%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='users'" label="头像" prop="image">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.image?ruleForm.image:''"
						@change="usersimageUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"20px 0 0"}'>
					<el-button class="btn3" :style='{"border":"0px solid #ccc","cursor":"pointer","padding":"0 10px","margin":"0 10px 0 0","color":"#fff","borderRadius":"4px","background":"#32c67a","width":"auto","fontSize":"16px","minWidth":"110px","height":"40px"}' type="primary" @click="onUpdateHandler">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
						确定
					</el-button>
				</el-form-item>
		</el-form>
	</div>
</template>
<script>
// 校验引入
import { 
	isIntNumer,
	isMobile,
} from "@/utils/validate";

export default {
	data() {
		return {
			ruleForm: {},
			flag: '',
			usersFlag: false,
			jiazhangxingbieOptions: [],
			jiazhangxueshengzhanghaoOptions: [],
			xueshengxingbieOptions: [],
			xueshengjiazhangzhanghaoOptions: [],
			bianchengjiaoshixingbieOptions: [],
		};
	},
	mounted() {
		var table = this.$storage.get("sessionTable");
		this.flag = table;
		this.$http({
			url: `${this.$storage.get("sessionTable")}/session`,
			method: "get"
		}).then(({ data }) => {
			if (data && data.code === 0) {
				this.ruleForm = data.data;
			} else {
				this.$message.error(data.msg);
			}
		});
		this.jiazhangxingbieOptions = "男,女".split(',')
		this.$http({
			url: `option/xuesheng/xueshengzhanghao`,
			method: "get"
		}).then(({ data }) => {
			if (data && data.code === 0) {
				this.jiazhangxueshengzhanghaoOptions = data.data;
			} else {
				this.$message.error(data.msg);
			}
		});
		this.xueshengxingbieOptions = "男,女".split(',')
		this.$http({
			url: `option/jiazhang/jiazhangzhanghao`,
			method: "get"
		}).then(({ data }) => {
			if (data && data.code === 0) {
				this.xueshengjiazhangzhanghaoOptions = data.data;
			} else {
				this.$message.error(data.msg);
			}
		});
		this.bianchengjiaoshixingbieOptions = "男,女".split(',')
	},
	methods: {
		// 下二随
		jiazhangxueshengzhanghaoChange () {
			this.$http({
				url: `follow/xuesheng/xueshengzhanghao?columnValue=`+ this.ruleForm.xueshengzhanghao,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					if(data.data.xueshengxingming){
						this.ruleForm.xueshengxingming = data.data.xueshengxingming
					}
				} else {
					this.$message.error(data.msg);
				}
			});
		},
		// 下二随
		xueshengjiazhangzhanghaoChange () {
			this.$http({
				url: `follow/jiazhang/jiazhangzhanghao?columnValue=`+ this.ruleForm.jiazhangzhanghao,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					if(data.data.jiazhangxingming){
						this.ruleForm.jiazhangxingming = data.data.jiazhangxingming
					}
					if(data.data.jiazhangdianhua){
						this.ruleForm.jiazhangdianhua = data.data.jiazhangdianhua
					}
				} else {
					this.$message.error(data.msg);
				}
			});
		},
		jiazhangtouxiangUploadChange(fileUrls) {
			this.ruleForm.touxiang = fileUrls;
		},
		xueshengtouxiangUploadChange(fileUrls) {
			this.ruleForm.touxiang = fileUrls;
		},
		bianchengjiaoshitouxiangUploadChange(fileUrls) {
			this.ruleForm.touxiang = fileUrls;
		},
		usersimageUploadChange(fileUrls) {
			this.ruleForm.image = fileUrls;
		},
		onUpdateHandler() {









			if( 'jiazhang' ==this.flag && this.ruleForm.jiazhangdianhua&&(!isMobile(this.ruleForm.jiazhangdianhua))){
				this.$message.error(`家长电话应输入手机格式`);
				return
			}


			if(this.ruleForm.touxiang!=null) {
				this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
			}











			if(this.ruleForm.touxiang!=null) {
				this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
			}



















			if(this.ruleForm.touxiang!=null) {
				this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
			}






			if( 'bianchengjiaoshi' ==this.flag && this.ruleForm.shoujihaoma&&(!isMobile(this.ruleForm.shoujihaoma))){
				this.$message.error(`手机号码应输入手机格式`);
				return
			}






			if( 'bianchengjiaoshi' ==this.flag && this.ruleForm.thumbsupnum&&(!isIntNumer(this.ruleForm.thumbsupnum))){
				this.$message.error(`赞应输入整数`);
				return
			}


			if( 'bianchengjiaoshi' ==this.flag && this.ruleForm.crazilynum&&(!isIntNumer(this.ruleForm.crazilynum))){
				this.$message.error(`踩应输入整数`);
				return
			}




			if( 'bianchengjiaoshi' ==this.flag && this.ruleForm.discussnum&&(!isIntNumer(this.ruleForm.discussnum))){
				this.$message.error(`评论数应输入整数`);
				return
			}


			if( 'bianchengjiaoshi' ==this.flag && this.ruleForm.storeupnum&&(!isIntNumer(this.ruleForm.storeupnum))){
				this.$message.error(`收藏数应输入整数`);
				return
			}
			if('users'==this.flag && this.ruleForm.username.trim().length<1) {
				this.$message.error(`用户名不能为空`);
				return	
			}
			if(this.flag=='users'){
				this.ruleForm.image = this.ruleForm.image.replace(new RegExp(this.$base.url,"g"),"")
			}
			this.$http({
				url: `${this.$storage.get("sessionTable")}/update`,
				method: "post",
				data: this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "修改信息成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							if(this.flag=='users'){
								this.$storage.set('headportrait',this.ruleForm.image)
							}
						}
					});
				} else {
					this.$message.error(data.msg);
				}
			});
		}
	}
};
</script>
<style lang="scss" scoped>
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
				padding: 0 10px 0 0;
				color: #6e6e6e;
				font-weight: 500;
				width: 180px;
				font-size: 15px;
				line-height: 40px;
				text-align: right;
			}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
				border: 1px solid #E8E8E8;
				border-radius: 0px;
				padding: 0 12px;
				color: #666;
				width: 100%;
				font-size: 15px;
				min-width: 50%;
				height: 40px;
			}
	
	.add-update-preview .el-select /deep/ .el-input__inner {
				border: 1px solid #E8E8E8;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				width: 100%;
				font-size: 15px;
				height: 40px;
			}
	
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
				border: 1px solid #E8E8E8;
				border-radius: 0px;
				padding: 0 10px 0 30px;
				color: #666;
				background: #fff;
				width: 100%;
				font-size: 15px;
				height: 40px;
			}
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
				border: 1px solid #E8E8E8;
				cursor: pointer;
				border-radius: 0px;
				color: #666;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
				border: 1px solid #E8E8E8;
				cursor: pointer;
				border-radius: 0px;
				color: #666;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
				border: 1px solid #E8E8E8;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: #fff;
				width: auto;
				font-size: 15px;
				min-width: 100%;
				height: 120px;
			}
	
	.add-update-preview .btn3 {
				border: 0px solid #ccc;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0 10px 0 0;
				color: #fff;
				background: #32c67a;
				width: auto;
				font-size: 16px;
				min-width: 110px;
				height: 40px;
			}
	
	.add-update-preview .btn3:hover {
				opacity: 0.8;
			}
	
	.editor>.avatar-uploader {
		line-height: 0;
		height: 0;
	}
</style>
