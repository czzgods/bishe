<template>
	<div class="center-preview">
		<div class="center-title">{{ title }}</div>
		<div class="center-info">
			<div class="center-info-title">个人信息</div>
			<div class="img-box" v-if="userTableName=='jiazhang'">
				<img :src="sessionForm.touxiang?baseUrl + sessionForm.touxiang:require('@/assets/avator.png')">
			</div>
			<div class="img-box" v-if="userTableName=='xuesheng'">
				<img :src="sessionForm.touxiang?baseUrl + sessionForm.touxiang:require('@/assets/avator.png')">
			</div>
			<div class="img-box" v-if="userTableName=='bianchengjiaoshi'">
				<img :src="sessionForm.touxiang?baseUrl + sessionForm.touxiang:require('@/assets/avator.png')">
			</div>
			<div class="info-item1" v-if="userTableName=='jiazhang'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">家长账号：</div>
				<div class="text">{{sessionForm.jiazhangzhanghao}}</div>
			</div>
			<div class="info-item2" v-if="userTableName=='jiazhang'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">家长姓名：</div>
				<div class="text">{{sessionForm.jiazhangxingming}}</div>
			</div>
			<div class="info-item3" v-if="userTableName=='jiazhang'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">性别：</div>
				<div class="text">{{sessionForm.xingbie}}</div>
			</div>
			<div class="info-item4" v-if="userTableName=='jiazhang'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">家长电话：</div>
				<div class="text">{{sessionForm.jiazhangdianhua}}</div>
			</div>
			<div class="info-item5" v-if="userTableName=='jiazhang'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">学生账号：</div>
				<div class="text">{{sessionForm.xueshengzhanghao}}</div>
			</div>
			<div class="info-item6" v-if="userTableName=='jiazhang'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">学生姓名：</div>
				<div class="text">{{sessionForm.xueshengxingming}}</div>
			</div>
			<div class="info-item1" v-if="userTableName=='xuesheng'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">学生账号：</div>
				<div class="text">{{sessionForm.xueshengzhanghao}}</div>
			</div>
			<div class="info-item2" v-if="userTableName=='xuesheng'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">学生姓名：</div>
				<div class="text">{{sessionForm.xueshengxingming}}</div>
			</div>
			<div class="info-item3" v-if="userTableName=='xuesheng'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">性别：</div>
				<div class="text">{{sessionForm.xingbie}}</div>
			</div>
			<div class="info-item4" v-if="userTableName=='xuesheng'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">年龄：</div>
				<div class="text">{{sessionForm.nianling}}</div>
			</div>
			<div class="info-item5" v-if="userTableName=='xuesheng'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">家长账号：</div>
				<div class="text">{{sessionForm.jiazhangzhanghao}}</div>
			</div>
			<div class="info-item6" v-if="userTableName=='xuesheng'">
				<span class="icon iconfont icon-shouye-zhihui"></span>
				<div class="label">家长姓名：</div>
				<div class="text">{{sessionForm.jiazhangxingming}}</div>
			</div>
		
		</div>
	
		<el-tabs class="center-tabs" tab-position="left" @tab-click="handleClick">
			<el-tab-pane label="个人中心">
				<el-form class="center-preview-pv" ref="sessionForm" :model="sessionForm" :rules="rules" label-width="120px">
					<el-form-item class="center-item" v-if="userTableName=='jiazhang'" label="家长账号" prop="jiazhangzhanghao">
						<el-input v-model="sessionForm.jiazhangzhanghao" placeholder="家长账号" readonly></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiazhang'" label="家长姓名" prop="jiazhangxingming">
						<el-input v-model="sessionForm.jiazhangxingming" placeholder="家长姓名" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiazhang'" label="性别" prop="xingbie">
						<el-select v-model="sessionForm.xingbie" placeholder="请选择性别" >
							<el-option v-for="(item, index) in dynamicProp.xingbie" :key="index" :label="item" :value="item"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiazhang'" label="家长电话" prop="jiazhangdianhua">
						<el-input v-model="sessionForm.jiazhangdianhua" placeholder="家长电话" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiazhang'" label="头像" prop="touxiang">
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="sessionForm.touxiang?sessionForm.touxiang:''"
							@change="jiazhangtouxiangHandleAvatarSuccess"
							></file-upload>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiazhang'" label="学生账号" prop="xueshengzhanghao">
						<el-select v-model="sessionForm.xueshengzhanghao" placeholder="请选择学生账号"  @change="jiazhangxueshengzhanghaoChange" >
							<el-option v-for="(item, index) in dynamicProp.xueshengzhanghao" :key="index" :label="item" :value="item"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='jiazhang'" label="学生姓名" prop="xueshengxingming">
						<el-input v-model="sessionForm.xueshengxingming" placeholder="学生姓名" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='xuesheng'" label="学生账号" prop="xueshengzhanghao">
						<el-input v-model="sessionForm.xueshengzhanghao" placeholder="学生账号" readonly></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='xuesheng'" label="学生姓名" prop="xueshengxingming">
						<el-input v-model="sessionForm.xueshengxingming" placeholder="学生姓名" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='xuesheng'" label="头像" prop="touxiang">
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="sessionForm.touxiang?sessionForm.touxiang:''"
							@change="xueshengtouxiangHandleAvatarSuccess"
							></file-upload>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='xuesheng'" label="性别" prop="xingbie">
						<el-select v-model="sessionForm.xingbie" placeholder="请选择性别" >
							<el-option v-for="(item, index) in dynamicProp.xingbie" :key="index" :label="item" :value="item"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='xuesheng'" label="年龄" prop="nianling">
						<el-input v-model="sessionForm.nianling" placeholder="年龄" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='xuesheng'" label="家长账号" prop="jiazhangzhanghao">
						<el-select v-model="sessionForm.jiazhangzhanghao" placeholder="请选择家长账号"  @change="xueshengjiazhangzhanghaoChange" >
							<el-option v-for="(item, index) in dynamicProp.jiazhangzhanghao" :key="index" :label="item" :value="item"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='xuesheng'" label="家长姓名" prop="jiazhangxingming">
						<el-input v-model="sessionForm.jiazhangxingming" placeholder="家长姓名" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='xuesheng'" label="家长电话" prop="jiazhangdianhua">
						<el-input v-model="sessionForm.jiazhangdianhua" placeholder="家长电话" ></el-input>
					</el-form-item>
					<el-form-item class="center-item" v-if="userTableName=='xuesheng'" label="家庭住址" prop="jiatingzhuzhi">
						<el-input v-model="sessionForm.jiatingzhuzhi" placeholder="家庭住址" ></el-input>
					</el-form-item>
					<el-form-item class="center-btn-item">
						<div class="updateBtn" type="primary" @click="onSubmit('sessionForm')">
							<span class="icon iconfont icon-kaitongfuwu"></span>
							<span class="text">更新信息</span>
						</div>
						<div class="closeBtn" type="danger" @click="logout">
							<span class="icon iconfont icon-shanchu1"></span>
							<span class="text">取消</span>
						</div>
					</el-form-item>
				</el-form>
			</el-tab-pane>
			<el-tab-pane label="修改密码">
				<el-form class="center-preview-pv" ref="passwordForm" :model="passwordForm" :rules="passwordRules" label-width="120px">
					<el-form-item class="center-item" label="原密码" prop="password">
						<el-input type="password" v-model="passwordForm.password" placeholder="原密码"></el-input>
					</el-form-item>
					<el-form-item class="center-item" label="新密码" prop="newpassword">
						<el-input type="password" v-model="passwordForm.newpassword" placeholder="新密码"></el-input>
					</el-form-item>
					<el-form-item class="center-item" label="确认密码" prop="repassword">
						<el-input type="password" v-model="passwordForm.repassword" placeholder="确认密码"></el-input>
					</el-form-item>
					<el-form-item class="center-btn-item">
						<div class="updateBtn" type="primary" @click="updatePassword">
							<span class="icon iconfont icon-kaitongfuwu"></span>
							<span class="text">修改密码</span>
						</div>
					</el-form-item>
				</el-form>
			</el-tab-pane>
			<el-tab-pane label="聊天记录" style="width: 100%;" v-if="changeHasChat()">
				<div class="z-box">
					<div class="section-content" v-for="item in recordList" :key="item.id" @click.stop="chatClick(item)">
						<div class="chat-left">
							<img :src="item.picture?baseUrl + item.picture:require('@/assets/avator.png')">
							<div class="chat-info">
								<div class="chat-name">{{item.name}}</div>
								<div class="chat-text">
									<div class="chat-noread" v-if="item.notreadnum">{{item.notreadnum}}</div>
									{{item.content.split('/').length>1&&item.content.split('/')[0]=='upload'?'[图片]':item.content}}
								</div>
							</div>
						</div>
						<div class="chat-btn">
							<div class="friendBtn2" @click.stop="chatDel(item)">删除</div>
						</div>
					</div>
					<div class="noList" v-if="!recordList.length">
						暂无聊天记录
					</div>
				</div>
			
			</el-tab-pane>
			<el-tab-pane v-for="(item,index) in menuList" :key="index" v-if="hasBack(item.menu)" :label="item.child[0].menu" :name="item.child[0].tableName"></el-tab-pane>
			<el-tab-pane label="测试记录"></el-tab-pane>
			<el-tab-pane label="错题本"></el-tab-pane>
			<el-tab-pane label="我的收藏"></el-tab-pane>
		</el-tabs>

		<el-dialog :visible.sync="chatVisible" @close="clearChat" :title="nowname">
			<div class="chat-content" id="chat-content">
				<div v-bind:key="item.id" v-for="item in chatList">
					<div v-if="item.addtime" class="addtime">{{timeFormat(item.addtime)}}</div>
					<div v-if="item.uid==sessionForm.id" class="right-content">
						<el-alert v-if="item.format==1" class="text-content" :title="item.content" :closable="false"
							type="warning"></el-alert>
						<el-image v-else fit="cover" :src="item.content?baseUrl + item.content:''" style="width: 100px;height: 100px;" :preview-src-list="[item.content?baseUrl + item.content:'']"></el-image>
						<img :src="mypic?baseUrl + mypic:require('@/assets/avator.png')" alt="" style="width: 30px;border-radius: 50%;height: 30px;margin: 0 0 0 10px;" />
					</div>
					<div v-else class="left-content">
						<img :src="nowfpic?baseUrl + nowfpic:require('@/assets/avator.png')" alt="" style="width: 30px;border-radius: 50%;height: 30px;margin: 0 10px 0 0;" />
						<el-alert v-if="item.format==1" class="text-content" :title="item.content" :closable="false"
							type="success"></el-alert>
						<el-image v-else fit="cover" :src="item.content?baseUrl + item.content:''" style="width: 100px;height: 100px;" :preview-src-list="[item.content?baseUrl + item.content:'']"></el-image>
					</div>
					<div class="clear-float"></div>
				</div>
			</div>
			<div slot="footer" class="dialog-footer">
				<el-input @keydown.enter.native="addChat(null)" v-model="chatForm.content" placeholder="请输入内容" style="width: calc(100% - 180px);float: left;">
				</el-input>
				<el-button :disabled="chatForm.content?false:true" type="primary" @click="addChat(null)">发送</el-button>
				<el-upload style="display: inline-block;margin: 0 0 0 6px;" class="upload-demo" :action="uploadUrl" :on-success="uploadSuccess"
					:show-file-list="false">
					<el-button type="success">上传图片</el-button>
				</el-upload>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	import config from '@/config/config';
	import menu from '@/config/menu';
	import Vue from 'vue';
	import timeMethod from '@/common/timeMethod'
	import {
		WebsocketMixin
	} from '@/mixins/WebsocketMixin'
	export default {
		mixins: [WebsocketMixin],
		//数据集合
		data() {
			return {
				title: '个人中心',
				baseUrl: config.baseUrl,
				sessionForm: {},
				passwordForm: {},
				passwordRules: {
					password: [
						{
							required: true,
							message: "密码不能为空",
							trigger: "blur"
						}
					],
					newpassword: [
						{
							required: true,
							message: "新密码不能为空",
							trigger: "blur"
						}
					],
					repassword: [
						{
							required: true,
							message: "确认密码不能为空",
							trigger: "blur"
						}
					]
				},
				rules: {},
				menuList: [],
				disabled: false,
				uploadUrl: config.baseUrl + 'file/upload',
				imageUrl: '',
				headers: {Token: localStorage.getItem('frontToken')},
				userTableName: localStorage.getItem('UserTableName'),
				dynamicProp: {},
				recordList: [],
				chatVisible: false,
				nowfid: 0,
				nowfpic:'',
				nowname: '',
				mypic: localStorage.getItem('frontHeadportrait'),
				chatList: [],
				chatForm: {
					content: ''
				},
				hasChatList: [
					'jiazhang',
					'xuesheng',
					'bianchengjiaoshi',
					'kechengxinxi',
				],
			}
		},
		created() {
			let menus = menu.list()
			for(let x in menus){
				if(menus[x].tableName == this.userTableName){
					for(let i in menus[x].backMenu){
						if(menus[x].backMenu[i].menu=='考试管理'){
							menus[x].backMenu.splice(i,1)
						}
					}
					this.menuList = menus[x].backMenu
				}
			}
			if ('jiazhang' == this.userTableName) {
				this.$set(this.sessionForm, 'jiazhangzhanghao', null);
			}
			if ('jiazhang' == this.userTableName) {
				this.$set(this.sessionForm, 'jiazhangxingming', null);
			}
			if ('jiazhang' == this.userTableName) {
				this.$set(this.sessionForm, 'mima', null);
			}
			if ('jiazhang' == this.userTableName) {
				this.$set(this.sessionForm, 'xingbie', null);
			}
			if ('jiazhang' == this.userTableName) {
				this.$set(this.sessionForm, 'jiazhangdianhua', null);
			}
			if ('jiazhang' == this.userTableName) {
				this.$set(this.sessionForm, 'touxiang', null);
			}
			if ('jiazhang' == this.userTableName) {
				this.$set(this.sessionForm, 'xueshengzhanghao', null);
			}
			if ('jiazhang' == this.userTableName) {
				this.$set(this.sessionForm, 'xueshengxingming', null);
			}
			if ('xuesheng' == this.userTableName) {
				this.$set(this.sessionForm, 'xueshengzhanghao', null);
			}
			if ('xuesheng' == this.userTableName) {
				this.$set(this.sessionForm, 'xueshengxingming', null);
			}
			if ('xuesheng' == this.userTableName) {
				this.$set(this.sessionForm, 'mima', null);
			}
			if ('xuesheng' == this.userTableName) {
				this.$set(this.sessionForm, 'touxiang', null);
			}
			if ('xuesheng' == this.userTableName) {
				this.$set(this.sessionForm, 'xingbie', null);
			}
			if ('xuesheng' == this.userTableName) {
				this.$set(this.sessionForm, 'nianling', null);
			}
			if ('xuesheng' == this.userTableName) {
				this.$set(this.sessionForm, 'jiazhangzhanghao', null);
			}
			if ('xuesheng' == this.userTableName) {
				this.$set(this.sessionForm, 'jiazhangxingming', null);
			}
			if ('xuesheng' == this.userTableName) {
				this.$set(this.sessionForm, 'jiazhangdianhua', null);
			}
			if ('xuesheng' == this.userTableName) {
				this.$set(this.sessionForm, 'jiatingzhuzhi', null);
			}

			if ('jiazhang' == this.userTableName) {
				this.$set(this.rules, 'jiazhangdianhua', [{ required: false, validator: this.$validate.isMobile, trigger: 'blur' }]);
			}

			this.init();
			this.sessionForm = JSON.parse(localStorage.getItem('sessionForm'))
		},
		//方法集合
		methods: {
			init() {
				if ('jiazhang' == this.userTableName) {
					this.dynamicProp.xingbie = '男,女'.split(',');
				}
				if ('jiazhang' == this.userTableName) {
					this.$http.get('option/xuesheng/xueshengzhanghao', {emulateJSON: true}).then(res => {
						if (res.data.code == 0) {
							this.dynamicProp.xueshengzhanghao = res.data.data;
							this.$forceUpdate()
						}
					});
				}    
				if ('xuesheng' == this.userTableName) {
					this.dynamicProp.xingbie = '男,女'.split(',');
				}
				if ('xuesheng' == this.userTableName) {
					this.$http.get('option/jiazhang/jiazhangzhanghao', {emulateJSON: true}).then(res => {
						if (res.data.code == 0) {
							this.dynamicProp.jiazhangzhanghao = res.data.data;
							this.$forceUpdate()
						}
					});
				}    
			},
			setSession(){
				localStorage.setItem('sessionForm',JSON.stringify(this.sessionForm))
			},
			jiazhangxueshengzhanghaoChange(value) {
				this.$http.get('follow/xuesheng/xueshengzhanghao', {params: {columnValue: value}}).then(res => {
					if (res.data.code == 0) {
						this.sessionForm.xueshengxingming = res.data.data.xueshengxingming;
					}
				});
			},
			xueshengjiazhangzhanghaoChange(value) {
				this.$http.get('follow/jiazhang/jiazhangzhanghao', {params: {columnValue: value}}).then(res => {
					if (res.data.code == 0) {
						this.sessionForm.jiazhangxingming = res.data.data.jiazhangxingming;
						this.sessionForm.jiazhangdianhua = res.data.data.jiazhangdianhua;
					}
				});
			},
			onSubmit(formName) {
				if(`jiazhang` == this.userTableName && this.sessionForm.touxiang!=null){
					this.sessionForm.touxiang = this.sessionForm.touxiang.replace(new RegExp(this.$config.baseUrl,"g"),"");
				}
				if(`xuesheng` == this.userTableName && this.sessionForm.touxiang!=null){
					this.sessionForm.touxiang = this.sessionForm.touxiang.replace(new RegExp(this.$config.baseUrl,"g"),"");
				}
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$http.post(this.userTableName + '/update', this.sessionForm).then(res => {
							if (res.data.code == 0) {
								this.setSession()
								this.$message({
									message: '更新成功',
									type: 'success',
									duration: 1500
								});
							}
						});
					} else {
						return false;
					}
				});
			},
			jiazhangtouxiangHandleAvatarSuccess(fileUrls) {
				this.sessionForm.touxiang = fileUrls;
			},
			xueshengtouxiangHandleAvatarSuccess(fileUrls) {
				this.sessionForm.touxiang = fileUrls;
			},
			handleClick(tab, event) {
				switch(event.target.outerText) {
					case '个人中心':
						tab.$router.push('/index/center');
						break;
					case '修改密码':
						this.passwordForm = {
							password: '',
							newpassword: '',
							repassword: '',
						}
						this.$forceUpdate()
						break;
					case '聊天记录':
						this.getRecordList()
						break;
					case '测试记录':
						tab.$router.push('/index/examList');
						break;
					case '错题本':
						tab.$router.push('/index/examRecord/0');
						break;
					case '我的收藏':
						localStorage.setItem('storeupType', 1);
						tab.$router.push('/index/storeup');
						break;
					default:
						tab.$router.push(`/index/${tab.name}?centerType=1`);
				}

				this.title = event.target.outerText;
			},
			changeHasChat(){
				let table = localStorage.getItem('frontSessionTable')
				for(let x in this.hasChatList){
					if(this.hasChatList[x]==table){
						return true
					}
				}
				return false
			},
			getRecordList() {
				this.$http.get('friend/page2', {
					params: {
						uid: Number(localStorage.getItem('frontUserid')),
						type: 2
					}
				}).then(res => {
					if (res.data && res.data.code == 0) {
						this.recordList = res.data.data.list
					}
				})
			},
			chatClick(row) {
				this.nowfid = row.fid
				this.nowfpic = row.picture
				this.nowname = row.name
				this.initWebSocket(this.nowfid)
				this.getChatList()
				this.chatVisible = true
			},
			chatDel(row){
				this.$confirm('是否删除聊天记录？').then(_ => {
					let arr = []
					let brr = []
					this.$http.get('chatmessage/list',{params: {
						page: 1,
						limit: 10000,
						uid: Number(localStorage.getItem('frontUserid')),
						fid: row.fid
					}}).then(rs=>{
						if(rs.data.data.list.length){
							for(let x in rs.data.data.list){
								arr.push(rs.data.data.list[x].id)
							}
						}
						this.$http.get('chatmessage/list',{params: {
							page: 1,
							limit: 10000,
							uid: row.fid,
							fid: Number(localStorage.getItem('frontUserid'))
						}}).then(rs2=>{
							if(rs2.data.data.list.length){
								for(let x in rs2.data.data.list){
									arr.push(rs2.data.data.list[x].id)
								}
							}
							if(arr.length){
								this.$http.post('chatmessage/delete',arr).then(()=>{})
							}
							this.$http.get('friend/list',{params: {
								page: 1,
								limit: 10000,
								uid: row.fid,
								fid: Number(localStorage.getItem('frontUserid')),
								type: 0
							}}).then(rs3=>{
								if(rs3.data.data.list.length){
									for(let x in rs3.data.data.list){
										brr.push(rs3.data.data.list[x].id)
									}
								}
								this.$http.get('friend/list',{params: {
									page: 1,
									limit: 10000,
									fid: row.fid,
									uid: Number(localStorage.getItem('frontUserid')),
									type: 0
								}}).then(rs4=>{
									if(rs4.data.data.list.length){
										for(let x in rs4.data.data.list){
											brr.push(rs4.data.data.list[x].id)
										}
									}
									this.$http.get('friend/list',{params: {
										page: 1,
										limit: 10000,
										fid: row.fid,
										uid: Number(localStorage.getItem('frontUserid')),
										type: 2
									}}).then(rs5=>{
										if(rs5.data.data.list.length){
											for(let x in rs5.data.data.list){
												brr.push(rs5.data.data.list[x].id)
											}
										}
										this.$http.get('friend/list',{params: {
											page: 1,
											limit: 10000,
											uid: row.fid,
											fid: Number(localStorage.getItem('frontUserid')),
											type: 2
										}}).then(rs6=>{
											if(rs6.data.data.list.length){
												for(let x in rs6.data.data.list){
													brr.push(rs6.data.data.list[x].id)
												}
											}
											this.$http.post('friend/delete',brr).then(()=>{
												this.$message.success('删除成功')
												this.getRecordList()
											})
										})
									})
								})
							})
						})
					})
				}).catch(_ => {});
			},
			websocketOnopen: function() {
				
			},
			websocketOnmessage:function(e) {
				this.getChatList()
			},
			getChatList() {
				this.$http.get('chatmessage/mlist', {
					params: {
						page: 1,
						limit: 1000,
						uid: Number(localStorage.getItem('frontUserid')),
						fid: this.nowfid
					}
				}).then(res => {
					if (res.data && res.data.code == 0) {
						this.chatList = this.formatMessages(res.data.data.list)
						let div = document.getElementsByClassName('chat-content')[0]
						setTimeout(() => {
							if (div)
								div.scrollTop = div.scrollHeight
						}, 0)
					}
				})
			},
			formatMessages(messages) {
				let lastTime = null;
				messages.forEach((message, index) => {
					const currentTime = new Date(message.addtime).getTime();
					if (lastTime !== null) {
						const timeDiff = (currentTime - lastTime) / 1000 / 60; // 转换为分钟
						if (timeDiff < 3) {
							message.addtime = ''; // 如果小于3分钟，不显示时间
						}
					}
					lastTime = currentTime;
				});
				return messages;
			},
			timeFormat(time) {
				const Time = timeMethod.getTime(time).split("T");
				//当前消息日期属于周
				const week = timeMethod.getDateToWeek(time);
				//当前日期0时
				const nti = timeMethod.setTimeZero(timeMethod.getNowTime());
				//消息日期当天0时
				const mnti = timeMethod.setTimeZero(timeMethod.getTime(time));
				//计算日期差值
				const diffDate = timeMethod.calculateTime(nti, mnti);
				//本周一日期0时 （后面+1是去除当天时间）
				const fwnti = timeMethod.setTimeZero(timeMethod.countDateStr(-timeMethod.getDateToWeek(timeMethod
					.getNowTime()).weekID + 1));
				//计算周日期差值
				const diffWeek = timeMethod.calculateTime(mnti, fwnti);

				if (diffDate === 0) { //消息发送日期减去当天日期如果等于0则是当天时间
					return Time[1].slice(0, 5);
				} else if (diffDate < 172800000) { //当前日期减去消息发送日期小于2天（172800000ms）则是昨天-  一天最大差值前天凌晨00:00:00到今天晚上23:59:59
					return "昨天 " + Time[1].slice(0, 5);
				} else if (diffWeek >= 0) { //消息日期减去本周一日期大于0则是本周
					return week.weekName;
				} else { //其他时间则是日期
					return Time[0].slice(5, 10);
				}
			},
			clearChat(){
				this.websocketOnclose();
				this.chatList = []
				this.getRecordList()
			},
			uploadSuccess(res) {
				if (res.code == 0) {
					this.addChat('upload/' + res.file);
				}
			},
			addChat(ask=null) {
				this.$http.post('chatmessage/add', {
					uid: Number(localStorage.getItem('frontUserid')),
					fid: this.nowfid,
					content: ask?ask:this.chatForm.content,
					format: ask?2:1
				}).then(res2 => {
					this.websocketSend(ask?ask:this.chatForm.content)
					this.chatForm = {
						content: ''
					}
					this.getChatList()
				})
			},
			async updatePassword(){
				this.$refs["passwordForm"].validate(async valid => {
					if (valid) {
						var password = "";
						if (this.sessionForm.mima) {
							password = this.sessionForm.mima;
						} else if (this.sessionForm.password) {
							password = this.sessionForm.password;
						}
						if (this.userTableName == 'jiazhang') {
						}
						if (this.userTableName == 'xuesheng') {
						}
						if (this.passwordForm.password != password) {
							this.$message.error("原密码错误");
							return;
						}
						if (this.passwordForm.newpassword != this.passwordForm.repassword) {
							this.$message.error("两次密码输入不一致");
							return;
						}
						if (this.passwordForm.newpassword == this.passwordForm.password) {
							this.$message.error("新密码与原密码相同！");
							return;
						}
						this.sessionForm.password = this.passwordForm.newpassword;
						this.sessionForm.mima = this.passwordForm.newpassword;
						this.$http.post(`${this.userTableName}/update`,this.sessionForm).then(({data})=>{
							if (data && data.code === 0) {
								this.$message({
									message: "修改密码成功,下次登录系统生效",
									type: "success",
									duration: 1500,
									onClose: () => {
									}
								});
								this.setSession()
							} else {
								this.$message.error(data.msg);
							}
						});
					}
				})
			},
			logout() {
				localStorage.clear();
				Vue.http.headers.common['Token'] = "";
				this.$router.push('/index/home');
				this.activeIndex = '0'
				localStorage.setItem('keyPath', this.activeIndex)
				this.$forceUpdate()
				this.$message({
					message: '登出成功',
					type: 'success',
					duration: 1500,
				});
			},
			hasBack(name){
				switch(name){
					case '试题库管理':
						return false
						break;
					case '试题管理':
						return false
						break;
					case '我的收藏管理':
						return false
						break;
					default:
						return true
				}
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.center-preview {
		margin: 10px auto;
		background: #fff;
		width: 100%;
		position: relative;
		.center-title {
			margin: 0;
			color: #fff;
			background: url(http://codegen.caihongy.cn/20241119/91b3da33f957476e8c833cb4ebc67d27.png) center center/cover no-repeat ;
			width: 100%;
			font-size: 30px;
			line-height: 160px;
			text-align: center;
			height: 240px;
		}
		.center-info {
			border-radius: 10px;
			padding: 0 16%;
			box-shadow: 0 0px 0px rgba(0, 0, 0, 0.3);
			margin: -110px auto 60px;
			background: none;
			display: flex;
			width: 100%;
			flex-wrap: wrap;
			height: auto;
			.center-info-title {
				color: #333;
				display: none;
				width: 100%;
				font-size: 15px;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				line-height: 44px;
				border-style: solid;
				height: 44px;
			}
			.img-box {
				width: 100%;
				font-size: 0;
				border-color: #efefef;
				border-width: 0;
				border-style: solid;
				height: auto;
				img {
					border-radius: 100%;
					margin: 10px auto;
					object-fit: cover;
					display: block;
					width: 180px;
					border-color: #efefef;
					border-width: 0 0 1px 0;
					border-style: solid;
					height: 180px;
				}
			}
			.info-item1 {
				padding: 10px 20px 20px;
				display: flex;
				width: 100%;
				line-height: 40px;
				justify-content: center;
				height: auto;
				.icon {
					padding: 0 5px;
					color: #333;
					display: none;
					font-size: 14px;
				}
				.label {
					padding: 0 5px 0 0;
					color: #333;
					font-size: 22px;
				}
				.text {
					color: #333;
					font-size: 22px;
				}
			}
			.info-item2 {
				padding: 0 10px;
				display: flex;
				width: 20%;
				line-height: 40px;
				justify-content: center;
				height: auto;
				.icon {
					padding: 0 5px;
					color: #333;
					display: none;
					font-size: 14px;
				}
				.label {
					padding: 0 10px 0 0;
					color: #000;
					font-size: 15px;
				}
				.text {
					color: #000;
					font-size: 15px;
					text-align: right;
				}
			}
			.info-item3 {
				padding: 0 10px;
				display: flex;
				width: 20%;
				line-height: 40px;
				justify-content: center;
				height: auto;
				.icon {
					padding: 0 5px;
					color: #333;
					display: none;
					font-size: 14px;
				}
				.label {
					padding: 0 10px 0 0;
					color: #000;
					font-size: 15px;
				}
				.text {
					color: #000;
					font-size: 15px;
					text-align: right;
				}
			}
			.info-item4 {
				padding: 0 10px;
				display: flex;
				width: 20%;
				line-height: 40px;
				justify-content: center;
				height: auto;
				.icon {
					padding: 0 5px;
					color: #333;
					display: none;
					font-size: 14px;
				}
				.label {
					padding: 0 10px 0 0;
					color: #000;
					font-size: 15px;
				}
				.text {
					color: #000;
					font-size: 15px;
					text-align: right;
				}
			}
			.info-item5 {
				padding: 0 10px;
				display: flex;
				width: 20%;
				line-height: 40px;
				justify-content: center;
				height: auto;
				.icon {
					padding: 0 5px;
					color: #333;
					display: none;
					font-size: 14px;
				}
				.label {
					padding: 0 10px 0 0;
					color: #000;
					font-size: 15px;
				}
				.text {
					color: #000;
					font-size: 15px;
					text-align: right;
				}
			}
			.info-item6 {
				padding: 0 10px;
				display: flex;
				width: 20%;
				line-height: 40px;
				justify-content: center;
				height: auto;
				.icon {
					padding: 0 5px;
					color: #333;
					display: none;
					font-size: 14px;
				}
				.label {
					padding: 0 10px 0 0;
					color: #000;
					font-size: 15px;
				}
				.text {
					color: #000;
					font-size: 15px;
					text-align: right;
				}
			}
		}
		.center-tabs.el-tabs {
			background: #F2F3F7;
			display: flex;
			flex-wrap: wrap;
			/deep/ .el-tabs__header {
				padding: 20px 16% 0;
				margin: 0;
				overflow: auto;
				background: #fff;
				display: flex;
				width: 100%;
				border-color: #eee;
				border-width: 2px 0 0;
				justify-content: center;
				position: relative;
				float: left;
				border-style: solid;
			}
			/deep/ .el-tabs__header .el-tabs__item {
				padding: 0 10px;
				margin: 0 20px;
				color: #000;
				background: none;
				font-weight: 500;
				display: inline-block;
				font-size: 16px;
				border-color: transparent;
				line-height: 40px;
				position: relative;
				text-align: center;
				height: 40px;
			}
			/deep/ .el-tabs__header .el-tabs__item:hover {
				padding: 0 10px;
				margin: 0 20px;
				color: #0AB8C1;
				font-weight: 500;
				display: inline-block;
				font-size: 16px;
				border-color: #0AB8C1;
				line-height: 40px;
				background: none;
				border-width: 0 0 2px;
				position: relative;
				border-style: solid;
				text-align: center;
				height: 40px;
			}
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				padding: 0 10px;
				margin: 0 20px;
				color: #0AB8C1;
				font-weight: 500;
				display: inline-block;
				font-size: 16px;
				border-color: #0AB8C1;
				line-height: 40px;
				background: none;
				border-width: 0 0 2px;
				position: relative;
				border-style: solid;
				text-align: center;
				height: 40px;
			}
			/deep/ .el-tabs__content {
				padding: 10px;
				margin: 0 auto;
				background: none;
				display: flex;
				width: 68%;
				flex-wrap: wrap;
			}
			/deep/ .el-tabs__content .el-tab-pane {
				display: flex;
				width: 100%;
				flex-wrap: wrap;
			}
			& /deep/ .el-tabs__header {
				.el-tabs__nav{
					overflow: auto;
				}
				::-webkit-scrollbar {
					-webkit-appearance: none;
					width: 6px;
					height: 6px;
				}
				::-webkit-scrollbar-track {
					background: rgba(0, 0, 0, 0.1);
					border-radius: 0;
				}
				::-webkit-scrollbar-thumb {
					cursor: pointer;
					border-radius: 5px;
					background: rgba(0, 0, 0, 0.15);
					transition: color 0.2s ease;
				}
				::-webkit-scrollbar-thumb:hover {
					background: rgba(0, 0, 0, 0.3);
				}
				.el-tabs__nav-wrap {
					margin: 0;
					&::after {
						content: none;
					}
				}
				.el-tabs__active-bar {
					display: none !important;
				}
			}
			.center-preview-pv {
				.center-item.el-form-item {
					padding: 10px;
					margin: 0 0 10px;
					background: none;
					display: inline-block;
					width: 49%;
					/deep/ .el-form-item__label {
						padding: 0 10px 0 0;
						color: #000;
						font-weight: 500;
						width: 120px;
						font-size: 14px;
						line-height: 40px;
						text-align: right;
					}
					.el-form-item__content {
						margin-left: 120px;
					}
					.el-input {
						width: 100%;
					}
					.el-input /deep/ .el-input__inner {
						border: 1px solid #E2E3E5;
						border-radius: 0;
						padding: 0 12px;
						box-shadow: 0 0 0px rgba(64, 158, 255, .5);
						outline: none;
						color: #000;
						width: 80%;
						font-size: 15px;
						height: 40px;
					}
					.el-input /deep/ .el-input__inner[readonly="readonly"] {
						border: 1px solid #E2E3E5;
						cursor: not-allowed;
						border-radius: 0;
						padding: 0 12px;
						box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
						outline: none;
						color: #000;
						background: #f8f8f8;
						width: 80%;
						font-size: 15px;
						height: 40px;
					}
					.el-select {
						width: 80%;
					}
					.el-select /deep/ .el-input__inner {
						border: 1px solid #E2E3E5;
						border-radius: 0;
						padding: 0 10px;
						box-shadow: 0 0 0px rgba(64, 158, 255, .5);
						outline: none;
						color: #000;
						width: 100%;
						font-size: 15px;
						height: 40px;
					}
					.el-select /deep/ .is-disabled .el-input__inner {
						border: 1px solid #E2E3E5;
						cursor: not-allowed;
						border-radius: 0;
						padding: 0 10px;
						box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
						outline: none;
						color: #000;
						background: #f8f8f8;
						width: 100%;
						font-size: 15px;
						height: 40px;
					}
					.el-date-editor {
						width: 80%;
					}
					.el-date-editor /deep/ .el-input__inner {
						border: 1px solid #E2E3E5;
						border-radius: 0;
						padding: 0 10px 0 30px;
						box-shadow: 0 0 0px rgba(64, 158, 255, .5);
						outline: none;
						color: #000;
						width: 100%;
						font-size: 15px;
						height: 40px;
					}
					.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
						border: 1px solid #E2E3E5;
						cursor: not-allowed;
						border-radius: 0;
						padding: 0 10px 0 30px;
						box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
						outline: none;
						color: #000;
						background: #f8f8f8;
						width: 100%;
						font-size: 15px;
						height: 40px;
					}
					/deep/ .el-upload--picture-card {
						background: transparent;
						border: 0;
						border-radius: 0;
						width: auto;
						height: auto;
						line-height: initial;
						vertical-align: middle;
					}
					/deep/ .upload .upload-img {
						border: 1px dashed #999;
						cursor: pointer;
						border-radius: 6px;
						color: #999;
						width: 100px;
						font-size: 32px;
						line-height: 100px;
						text-align: center;
						height: 100px;
					}
					/deep/ .el-upload-list .el-upload-list__item {
						border: 1px dashed #999;
						cursor: pointer;
						border-radius: 6px;
						color: #999;
						width: 100px;
						font-size: 32px;
						line-height: 100px;
						text-align: center;
						height: 100px;
						font-size: 14px;
						line-height: 1.8;
					}
					/deep/ .el-upload .el-icon-plus {
						border: 1px dashed #999;
						cursor: pointer;
						border-radius: 6px;
						color: #999;
						width: 100px;
						font-size: 32px;
						line-height: 100px;
						text-align: center;
						height: 100px;
					}
					/deep/ .el-upload__tip {
						color: #838fa1;
						display: none;
					}
					/deep/ .el-input__inner::placeholder {
						color: #999;
						font-size: 14px;
					}
				}
				.center-btn-item {
					padding: 0;
					margin: 0;
					.updateBtn {
						border: 0;
						cursor: pointer;
						border-radius: 4px;
						padding: 0 15px;
						margin: 0 20px 0 0;
						outline: none;
						background: #5AC3B2;
						display: inline-block;
						width: auto;
						font-size: 14px;
						line-height: 35px;
						height: 35px;
						.icon {
							color: rgba(255, 255, 255, 1);
						}
						.text {
							color: rgba(255, 255, 255, 1);
						}
					}
					.updateBtn:hover {
						background: #5AC3B280;
						.icon {
							color: #fff;
						}
						.text {
							color: #fff;
						}
					}
					.closeBtn {
						border: 0px solid rgba(64, 158, 255, 1);
						cursor: pointer;
						padding: 0 15px;
						margin: 0 20px 0 0;
						display: inline-block;
						font-size: 14px;
						line-height: 35px;
						border-radius: 4px;
						outline: none;
						background: #858585;
						width: auto;
						text-align: center;
						min-width: 100px;
						height: 35px;
						.icon {
							color: #fff;
						}
						.text {
							color: #fff;
						}
					}
					.closeBtn:hover {
						background: #85858580;
						.icon {
							color: #fff;
						}
						.text {
							color: #fff;
						}
					}
				}
				.el-date-editor.el-input {
					width: auto;
				}
			}
		}
	}
	.z-box {
		padding: 20px;
		margin: 0 auto;
		width: 70%;
		.section-content {
			cursor: pointer;
			padding: 20px;
			margin: 0 0 20px;
			color: #333;
			display: flex;
			border-color: #efefef;
			box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.3);
			background: #fff;
			width: 100%;
			justify-content: space-between;
			border-width: 0;
			align-items: center;
			position: relative;
			border-style: solid;
			.chat-left {
				display: flex;
				align-items: center;
				img {
					border-radius: 50%;
					width: 60px;
					transition: all .4s;
					height: 60px;
				}
				.chat-info {
					margin: 0 0 0 10px;
					flex-direction: column;
					display: flex;
					align-items: flex-start;
					.chat-name {
						color: #888;
						flex: 1;
						font-weight: bold;
						font-size: 14px;
						transition: all .4s;
					}
					.chat-text {
						color: #888;
						flex: 1;
						display: flex;
						font-size: 12px;
						line-height: 1.5;
						align-items: center;
						transition: all .4s;
						.chat-noread {
							border-radius: 50%;
							padding: 0 5px;
							margin: 0 2px 0 0;
							color: #fff;
							background: #f00;
							width: auto;
							font-size: 12px;
							line-height: 16px;
							text-align: center;
							height: 16px;
						}
					}
				}
			}
			.chat-btn {
				flex-direction: column;
				display: flex;
				.friendBtn1 {
					padding: 0px 10px;
					margin: 0px;
					color: #fff;
					background: rgba(75, 223, 201,1);
					text-decoration: none;
					width: 80px;
					font-size: 12px;
					line-height: 30px;
					text-align: center;
					height: 30px;
				}
				.friendBtn1:hover {
					background: rgba(75, 223, 201,.5);
				}
				.friendBtn2 {
					padding: 0px 10px;
					margin: 0px;
					color: #fff;
					background: rgba(255, 85, 0, 1.0);
					text-decoration: none;
					width: 80px;
					font-size: 12px;
					line-height: 30px;
					text-align: center;
					height: 30px;
				}
				.friendBtn2:hover {
					background: rgba(255, 85, 0, 0.5);
				}
			}
		}
		.section-content:hover {
			color: #fff;
			background: #DF847F10;
			.chat-left {
				img {
					border-radius: 5px;
				}
				.chat-info {
					.chat-name {
						color: #000;
					}
					.chat-text {
						color: #000;
						font-size: 13px;
					}
				}
			}
		}
	}
	.chat-content {
		padding-bottom: 20px;
		width: 100%;
		margin-bottom: 10px;
		max-height: 300px;
		height: 300px;
		overflow-y: scroll;
		border: 1px solid #eeeeee;
		background: #fff;
	
		.addtime {
			width: 100%;
			text-align: center;
			font-size: 12px;
		}
	
		.left-content {
			float: left;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
			display: flex;
			align-items: center;
		}
	
		.right-content {
			float: right;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
			display: flex;
			align-items: center;
		}
	}
	
	.clear-float {
		clear: both;
	}
	.noList {
		color: #9e9e9e;
		width: 100%;
		text-align: center;
		padding: 60px 0;
	}
</style>
