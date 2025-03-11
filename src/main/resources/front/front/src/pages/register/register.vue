<template>
	<div>
		<div id="particles"></div>

		<div class="container">
			<el-form class='rgs-form animate__animated animate__bounce' v-if="pageFlag=='register'" ref="registerForm" :model="registerForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">基于springboot的少儿编程教育机构管理系统注册</p></div>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'" prop="jiazhangzhanghao">
						<div class="label" :class="changeRules('jiazhangzhanghao')?'required':''">家长账号：</div>
						<el-input v-model="registerForm.jiazhangzhanghao"  placeholder="请输入家长账号" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'" prop="jiazhangxingming">
						<div class="label" :class="changeRules('jiazhangxingming')?'required':''">家长姓名：</div>
						<el-input v-model="registerForm.jiazhangxingming"  placeholder="请输入家长姓名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'" prop="mima">
						<div class="label" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'" prop="mima2">
						<div class="label" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'" prop="xingbie">
						<div class="label" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="registerForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in jiazhangxingbieOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'" prop="jiazhangdianhua">
						<div class="label" :class="changeRules('jiazhangdianhua')?'required':''">家长电话：</div>
						<el-input v-model="registerForm.jiazhangdianhua"  placeholder="请输入家长电话" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'" prop="touxiang">
						<div class="label" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
							@change="jiazhangtouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'" prop="xueshengzhanghao">
						<div class="label" :class="changeRules('xueshengzhanghao')?'required':''">学生账号：</div>
						<el-select @change="jiazhangxueshengzhanghaoChange" v-model="registerForm.xueshengzhanghao" placeholder="请选择学生账号" >
							<el-option
								v-for="(item,index) in jiazhangxueshengzhanghaoOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'" prop="xueshengxingming">
						<div class="label" :class="changeRules('xueshengxingming')?'required':''">学生姓名：</div>
						<el-input v-model="registerForm.xueshengxingming" readonly placeholder="请输入学生姓名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'" prop="xueshengzhanghao">
						<div class="label" :class="changeRules('xueshengzhanghao')?'required':''">学生账号：</div>
						<el-input v-model="registerForm.xueshengzhanghao"  placeholder="请输入学生账号" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'" prop="xueshengxingming">
						<div class="label" :class="changeRules('xueshengxingming')?'required':''">学生姓名：</div>
						<el-input v-model="registerForm.xueshengxingming"  placeholder="请输入学生姓名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'" prop="mima">
						<div class="label" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'" prop="mima2">
						<div class="label" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'" prop="touxiang">
						<div class="label" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
							@change="xueshengtouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'" prop="xingbie">
						<div class="label" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="registerForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in xueshengxingbieOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'" prop="nianling">
						<div class="label" :class="changeRules('nianling')?'required':''">年龄：</div>
						<el-input v-model="registerForm.nianling"  placeholder="请输入年龄" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'" prop="jiazhangzhanghao">
						<div class="label" :class="changeRules('jiazhangzhanghao')?'required':''">家长账号：</div>
						<el-select @change="xueshengjiazhangzhanghaoChange" v-model="registerForm.jiazhangzhanghao" placeholder="请选择家长账号" >
							<el-option
								v-for="(item,index) in xueshengjiazhangzhanghaoOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'" prop="jiazhangxingming">
						<div class="label" :class="changeRules('jiazhangxingming')?'required':''">家长姓名：</div>
						<el-input v-model="registerForm.jiazhangxingming" readonly placeholder="请输入家长姓名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'" prop="jiazhangdianhua">
						<div class="label" :class="changeRules('jiazhangdianhua')?'required':''">家长电话：</div>
						<el-input v-model="registerForm.jiazhangdianhua" readonly placeholder="请输入家长电话" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'" prop="jiatingzhuzhi">
						<div class="label" :class="changeRules('jiatingzhuzhi')?'required':''">家庭住址：</div>
						<el-input v-model="registerForm.jiatingzhuzhi"  placeholder="请输入家庭住址" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='bianchengjiaoshi'" prop="jiaoshigonghao">
						<div class="label" :class="changeRules('jiaoshigonghao')?'required':''">教师工号：</div>
						<el-input v-model="registerForm.jiaoshigonghao"  placeholder="请输入教师工号" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='bianchengjiaoshi'" prop="jiaoshixingming">
						<div class="label" :class="changeRules('jiaoshixingming')?'required':''">教师姓名：</div>
						<el-input v-model="registerForm.jiaoshixingming"  placeholder="请输入教师姓名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='bianchengjiaoshi'" prop="mima">
						<div class="label" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='bianchengjiaoshi'" prop="mima2">
						<div class="label" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='bianchengjiaoshi'" prop="touxiang">
						<div class="label" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
							@change="bianchengjiaoshitouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='bianchengjiaoshi'" prop="xingbie">
						<div class="label" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="registerForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in bianchengjiaoshixingbieOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='bianchengjiaoshi'" prop="shanzhanglingyu">
						<div class="label" :class="changeRules('shanzhanglingyu')?'required':''">擅长领域：</div>
						<el-input v-model="registerForm.shanzhanglingyu"  placeholder="请输入擅长领域" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='bianchengjiaoshi'" prop="shoujihaoma">
						<div class="label" :class="changeRules('shoujihaoma')?'required':''">手机号码：</div>
						<el-input v-model="registerForm.shoujihaoma"  placeholder="请输入手机号码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='bianchengjiaoshi'" prop="jiaoxuejingyan">
						<div class="label" :class="changeRules('jiaoxuejingyan')?'required':''">教学经验：</div>
						<editor
							v-model="registerForm.jiaoxuejingyan" 
							class="editor" 
							action="file/upload">
						</editor>
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<el-button class="register_btn" type="primary" @click="submitForm('registerForm')">注册</el-button>
						</div>
						<div class="register-btn2">
							<router-link class="has_btn" to="/login">已有账号，直接登录</router-link>
						</div>
					</div>
				</div>
				<div class="idea1"></div>
				<div class="idea2"></div>
			</el-form>
		</div>
	</div>
</div>
</template>

<script>
	import 'animate.css';
	require('@/common/particles.js')

export default {
    //数据集合
    data() {
		return {
            pageFlag : '',
			tableName: '',
			registerForm: {},
			forgetForm: {},
			rules: {},
			requiredRules: {},
            jiazhangxingbieOptions: [],
            jiazhangxueshengzhanghaoOptions: [],
            xueshengxingbieOptions: [],
            xueshengjiazhangzhanghaoOptions: [],
            bianchengjiaoshixingbieOptions: [],
			particlesJson: {"retina_detect":true,"particles":{"number":{"density":{"value_area":800,"enable":true},"value":160},"move":{"straight":false,"random":true,"bounce":false,"enable":true,"attract":{"rotateX":600,"enable":false,"rotateY":600},"speed":1,"direction":"none","out_mode":"out"},"color":{"value":"#0AB8C1"},"shape":{"image":{"width":100,"src":"img/github.svg","height":100},"polygon":{"nb_sides":5},"type":"circle","stroke":{"color":"#000000","width":0}},"size":{"anim":{"size_min":0.3,"sync":false,"enable":false,"speed":4},"random":true,"value":3},"line_linked":{"width":1,"distance":150,"color":"#ffffff","opacity":0.4,"enable":false},"opacity":{"anim":{"opacity_min":0,"sync":false,"enable":true,"speed":1},"random":true,"value":1}},"interactivity":{"detect_on":"canvas","events":{"resize":true,"onclick":{"mode":"repulse","enable":true},"onhover":{"mode":"bubble","enable":true}},"modes":{"grab":{"distance":400,"line_linked":{"opacity":1}},"bubble":{"duration":2,"distance":250,"size":0,"opacity":0,"speed":3},"repulse":{"duration":0.4,"distance":400},"push":{"particles_nb":4},"remove":{"particles_nb":2}}}}
		}
    },
	mounted() {
		if(this.$route.query.pageFlag=='register'){
			this.tableName = this.$route.query.role;
			if(this.tableName=='jiazhang'){
				this.registerForm = {
					jiazhangzhanghao: '',
					jiazhangxingming: '',
					mima: '',
					mima2: '',
					xingbie: '',
					jiazhangdianhua: '',
					touxiang: '',
					xueshengzhanghao: '',
					xueshengxingming: '',
				}
			}
			if(this.tableName=='xuesheng'){
				this.registerForm = {
					xueshengzhanghao: '',
					xueshengxingming: '',
					mima: '',
					mima2: '',
					touxiang: '',
					xingbie: '',
					nianling: '',
					jiazhangzhanghao: '',
					jiazhangxingming: '',
					jiazhangdianhua: '',
					jiatingzhuzhi: '',
				}
			}
			if(this.tableName=='bianchengjiaoshi'){
				this.registerForm = {
					jiaoshigonghao: '',
					jiaoshixingming: '',
					mima: '',
					mima2: '',
					touxiang: '',
					xingbie: '',
					shanzhanglingyu: '',
					shoujihaoma: '',
					jianjie: '',
					jiaoxuejingyan: '',
					thumbsupnum: '',
					crazilynum: '',
					clicktime: '',
					discussnum: '',
					storeupnum: '',
				}
			}
			this.jiazhangxingbieOptions = "男,女".split(',');
			if ('jiazhang' == this.tableName) {
				this.rules.jiazhangdianhua = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
			}
			this.$http({
				url: `option/xuesheng/xueshengzhanghao`,
				method: "get",
				params: {}
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.jiazhangxueshengzhanghaoOptions = data.data;
				}
			});
			this.xueshengxingbieOptions = "男,女".split(',');
			this.$http({
				url: `option/jiazhang/jiazhangzhanghao`,
				method: "get",
				params: {}
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.xueshengjiazhangzhanghaoOptions = data.data;
				}
			});
			this.bianchengjiaoshixingbieOptions = "男,女".split(',');
			if ('bianchengjiaoshi' == this.tableName) {
				this.rules.shoujihaoma = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
			}
			if ('bianchengjiaoshi' == this.tableName) {
				this.rules.thumbsupnum = [{ required: true, validator: this.$validate.isIntNumer, trigger: 'blur' }];
			}
			if ('bianchengjiaoshi' == this.tableName) {
				this.rules.crazilynum = [{ required: true, validator: this.$validate.isIntNumer, trigger: 'blur' }];
			}
			if ('bianchengjiaoshi' == this.tableName) {
				this.rules.discussnum = [{ required: true, validator: this.$validate.isIntNumer, trigger: 'blur' }];
			}
			if ('bianchengjiaoshi' == this.tableName) {
				this.rules.storeupnum = [{ required: true, validator: this.$validate.isIntNumer, trigger: 'blur' }];
			}
		}
		particlesJS('particles',this.particlesJson);
	},
    created() {
		this.pageFlag = this.$route.query.pageFlag;
    },
    //方法集合
    methods: {
		changeRules(name){
			if(this.requiredRules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
        // 下二随
		jiazhangtouxiangUploadChange(fileUrls) {
			this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},
        jiazhangxueshengzhanghaoChange () {
			this.$http({
				url: `follow/xuesheng/xueshengzhanghao?columnValue=`+ this.registerForm.xueshengzhanghao,
				method: "get",
				params: {}
			}).then(({ data }) => {
				if (data && data.code === 0) {
					if(data.data.xueshengxingming){
						this.registerForm.xueshengxingming = data.data.xueshengxingming
					}
				}
			});
        },
		xueshengtouxiangUploadChange(fileUrls) {
			this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},
        xueshengjiazhangzhanghaoChange () {
			this.$http({
				url: `follow/jiazhang/jiazhangzhanghao?columnValue=`+ this.registerForm.jiazhangzhanghao,
				method: "get",
				params: {}
			}).then(({ data }) => {
				if (data && data.code === 0) {
					if(data.data.jiazhangxingming){
						this.registerForm.jiazhangxingming = data.data.jiazhangxingming
					}
					if(data.data.jiazhangdianhua){
						this.registerForm.jiazhangdianhua = data.data.jiazhangdianhua
					}
				}
			});
        },
		bianchengjiaoshitouxiangUploadChange(fileUrls) {
			this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},

		// 多级联动参数


		submitForm(formName) {
			this.$refs[formName].validate((valid) => {
				if (valid) {
					var url=this.tableName+"/register";
					if(`jiazhang` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
						this.$message.error(`两次密码输入不一致`);
						return
					}
					if(`xuesheng` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
						this.$message.error(`两次密码输入不一致`);
						return
					}
					if(`bianchengjiaoshi` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
						this.$message.error(`两次密码输入不一致`);
						return
					}
					this.$http.post(url, this.registerForm).then(res => {
						if (res.data.code === 0) {
							this.$message({
								message: '注册成功',
								type: 'success',
								duration: 1500,
								onClose: () => {
									this.$router.push('/login');
								}
							});
						} else {
							this.$message.error(res.data.msg);
						}
					});
				} else {
					return false;
				}
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		}
    }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.container {
		background-repeat: no-repeat;
		background-size: 100% 100%;
		background: url(http://codegen.caihongy.cn/20241031/a4f3a13e88a24a32af35565eff05efd0.png);
		display: flex;
		width: 100%;
		min-height: 100vh;
		justify-content: center;
		align-items: center;
		background-position: center center;
		position: relative;
		background: url(http://codegen.caihongy.cn/20241031/a4f3a13e88a24a32af35565eff05efd0.png);
		.rgs-form {
			border-radius: 20px;
			padding: 70px 20px 20px;
			box-shadow: 0 0px 0px rgba(64, 158, 255, .8);
			margin: 0;
			z-index: 2;
			background: url(http://codegen.caihongy.cn/20241031/382a552f717a4520817264ea42222b43.png) top center/100% auto no-repeat #fff;
			width: 40%;
			height: auto;
			.rgs-form2 {
				width: 100%;
				.title {
					margin: 0 0 120px;
					color: #fff;
					font-weight: bold;
					width: 100%;
					font-size: 30px;
					line-height: 1;
					text-align: center;
				}
				.subtitle {
					margin: 0 0 10px 0;
					text-shadow: 4px 4px 2px rgba(64, 158, 255, .5);
					color: rgba(64, 158, 255, 1);
					width: 100%;
					font-size: 20px;
					line-height: 44px;
					text-align: center;
				}
				.list-item {
					margin: 0 auto 15px;
					display: flex;
					width: 80%;
					/deep/.el-form-item__content {
						display: flex;
						width: 100%;
						align-items: flex-start;
						.label {
							color: #000;
							width: 80px;
							font-size: 13px;
							line-height: 44px;
						}
						
						.required {
							position: relative;
						}
						.required::after{
							color: red;
							left: -10px;
							position: absolute;
							content: "*";
						}
						.el-input {
							width: calc(100% - 80px);
						}
						.el-input .el-input__inner {
							border: 1px solid #D6D6D6;
							border-radius: 0;
							padding: 0 10px;
							box-shadow: 0 0 0px rgba(64, 158, 255, .5);
							outline: none;
							color: #000;
							width: 100%;
							font-size: 14px;
							border-width: 0 0 1px;
							height: 44px;
						}
						.el-input .el-input__inner:focus {
							border: 1px solid #0AB8C1;
							border-radius: 0;
							padding: 0 10px;
							box-shadow: 0 0 0px rgba(64, 158, 255, .5);
							outline: none;
							color: #000;
							width: 100%;
							font-size: 14px;
							border-width: 0 0 1px;
							height: 44px;
						}
						.el-select {
							width: calc(100% - 80px);
						}
						.el-select .el-input__inner {
							border: 1px solid #D6D6D6;
							border-radius: 0;
							padding: 0 10px;
							box-shadow: 0 0 0px rgba(64, 158, 255, .5);
							outline: none;
							color: #000;
							width: 100%;
							font-size: 14px;
							border-width: 0 0 1px;
							height: 44px;
						}
						.el-select .el-input__inner:focus {
							border: 1px solid #0AB8C1;
							border-radius: 0;
							padding: 0 10px;
							box-shadow: 0 0 0px rgba(64, 158, 255, .5);
							outline: none;
							color: #000;
							width: 100%;
							font-size: 14px;
							border-width: 0 0 1px;
							height: 44px;
						}
						.el-date-editor {
							width: calc(100% - 80px);
						}
						.el-date-editor .el-input__inner {
							border: 1px solid #D6D6D6;
							border-radius: 0;
							padding: 0 10px 0 30px;
							box-shadow: 0 0 0px rgba(64, 158, 255, .5);
							outline: none;
							color: #000;
							width: 100%;
							font-size: 14px;
							border-width: 0 0 1px;
							height: 44px;
						}
						.el-date-editor .el-input__inner:focus {
							border: 1px solid #0AB8C1;
							border-radius: 0;
							padding: 0 10px 0 30px;
							box-shadow: 0 0 0px rgba(85, 170, 0, 0.5);
							color: #000;
							width: 100%;
							font-size: 14px;
							border-width: 0 0 1px;
							height: 44px;
						}
						.el-upload--picture-card {
							background: transparent;
							border: 0;
							border-radius: 0;
							width: auto;
							height: auto;
							line-height: initial;
							vertical-align: middle;
						}
						.upload .upload-img {
							border: 1px solid #D6D6D6;
							cursor: pointer;
							border-radius: 0;
							color: #D6D6D6;
							width: 100px;
							font-size: 32px;
							line-height: 100px;
							text-align: center;
							height: 100px;
						}
						.el-upload-list .el-upload-list__item {
							border: 1px solid #D6D6D6;
							cursor: pointer;
							border-radius: 0;
							color: #D6D6D6;
							width: 100px;
							font-size: 32px;
							line-height: 100px;
							text-align: center;
							height: 100px;
							font-size: 14px;
							line-height: 1.8;
						}
						.el-upload .el-icon-plus {
							border: 1px solid #D6D6D6;
							cursor: pointer;
							border-radius: 0;
							color: #D6D6D6;
							width: 100px;
							font-size: 32px;
							line-height: 100px;
							text-align: center;
							height: 100px;
						}
						.el-upload__tip {
							color: #838fa1;
							display: none;
						}
						.emailInput {
							border: 1px solid #D6D6D6;
							border-radius: 0;
							padding: 0 10px;
							box-shadow: 0 0 0px rgba(64, 158, 255, .5);
							outline: none;
							color: #000;
							width: 100%;
							font-size: 14px;
							border-width: 0 0 1px;
							height: 44px;
						}
						.emailInput:focus {
							border: 1px solid #0AB8C1;
							border-radius: 0;
							padding: 0 10px;
							box-shadow: 0 0 0px rgba(64, 158, 255, .5);
							outline: none;
							color: #000;
							width: 100%;
							font-size: 14px;
							border-width: 0 0 1px;
							height: 44px;
						}
						.el-btn {
							border: 0;
							cursor: pointer;
							padding: 0;
							margin: 0;
							color: #fff;
							font-size: 12px;
							line-height: 44px;
							border-radius: 0;
							box-shadow: 0 0 0px rgba(64, 158, 255, .5);
							outline: none;
							background: #0AB8C1;
							width: 100px;
							height: 44px;
						}
						.el-btn:hover {
							opacity: 0.5;
						}
						
						.el-input__inner::placeholder {
							color: #123;
							font-size: 16px;
						}
						input::placeholder {
							color: #123;
							font-size: 16px;
						}
						.editor {
							width: calc(100% - 80px);
							height: auto;
						}
					}
				}
				.register-btn {
					margin: 0 auto;
					width: 80%;
				}
				.register-btn1 {
					padding: 0 0 0 80px;
					width: 100%;
				}
				.register-btn2 {
					width: 100%;
					text-align: center;
				}
				.register_btn {
					border: 0;
					cursor: pointer;
					padding: 0 10px;
					margin: 20px auto 5px;
					color: #fff;
					display: block;
					font-size: 16px;
					border-radius: 0;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					background: #0AB8C1;
					width: 100%;
					height: 44px;
				}
				.register_btn:hover {
					opacity: 0.5;
				}
				.has_btn {
					cursor: pointer;
					padding: 0 10%;
					color: #9E9E9E;
					display: inline-block;
					text-decoration: none;
					font-size: 12px;
					line-height: 1;
				}
				.has_btn:hover {
					opacity: 0.5;
				}
			}
			.idea1 {
				background: red;
				display: none;
				width: 100%;
				height: 40px;
			}
			.idea2 {
				background: blue;
				display: none;
				width: 100%;
				height: 40px;
			}
		}
	}
	
	#particles{
		background-repeat: no-repeat;
		z-index: 1;
		background-size: cover;
		width: 100%;
		position: absolute;
		background-position: 50% 50%;
		height: 100%;
	}
	::-webkit-scrollbar {
		display: none;
	}
</style>
