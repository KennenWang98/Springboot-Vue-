<template>
    <el-row class="container">
        <el-col :span="24" class="header">
			<el-col :span="18" class="logo" >
                {{sysName}}
			</el-col>
			<el-col :span="4" class="userInfo">
				<el-dropdown trigger="hover">
					<span class="el-dropdown-link userInfo-inner"> {{sysUserName}}</span>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item>设置</el-dropdown-item>
						<el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</el-col>
		</el-col>
        <el-col :span="24" class="main">
            <aside>
                <el-menu :default-active="$route.path" class="el-menu el-menu-vertical-demo"

                        unique-opened router >
                        <template v-for="(item,index) in $router.options.routes" v-if="!item.hidden">
						<el-submenu :index="index+''" v-if="!item.leaf">
							<template slot="title"><i :class="item.iconCls"></i>{{item.name}}</template>
							<el-menu-item v-for="child in item.children" :index="child.path" :key="child.path" v-if="!child.hidden">{{child.name}}</el-menu-item>
						</el-submenu>
						<el-menu-item v-if="item.leaf&&item.children.length>0" :index="item.children[0].path"><i :class="item.iconCls"></i>{{item.children[0].name}}</el-menu-item>
					</template>
                </el-menu>
            </aside>
			<section class="content-container">
				<div class="grid-content bg-purple-light">
					<el-col :span="24" class="breadcrumb-container">
						<strong class="title">{{$route.name}}</strong>
					</el-col>
					<el-col :span="24" class="content-wrapper">
						<transition name="fade" mode="out-in">
							<router-view></router-view>
						</transition>
					</el-col>
				</div>
			</section>
		</el-col>
    </el-row>
</template>

<script>
export default {
    data() {
			return {
				sysName:'中华写字课堂管理平台',
				sysUserName: '',
				sysUserAvatar: '',
				form: {
					name: '',
					region: '',
					date1: '',
					date2: '',
					delivery: false,
					type: [],
					resource: '',
					desc: ''
				}
			}
        },
    methods: {
			//退出登录
			logout: function () {
				let _this = this;
				this.$confirm('确认退出吗?', '提示', {
					//type: 'warning'
				}).then(() => {
					sessionStorage.removeItem('user');
					_this.$router.push('/login');
				}).catch(() => {

				});
			}
		},
    mounted() {
			let user = sessionStorage.getItem('user');
			if (user) {
				user = JSON.parse(user);
				this.sysUserName = user.name || '';
				//this.sysUserAvatar = user.avatar || '';
			}

		}
}
</script>

<style scoped lang="scss">
@import '../style/vars.scss';

    .container {
		position: absolute;
		top: 0px;
		bottom: 0px;
		width: 100%;
    }
    .header {
			height: 60px;
            line-height: 60px;
			background: $color-primary;
			color:#fff;
			.userInfo {
				text-align: right;
				padding-right: 35px;
				float: right;
				.userInfo-inner {
					cursor: pointer;
					color:#fff;
					img {
						width: 40px;
						height: 40px;
						border-radius: 20px;
						margin: 10px 0px 10px 10px;
						float: right;
					}
				}
			}
			.logo {
				height:60px;
				font-size: 22px;
				padding-left:20px;
				img {
					width: 40px;
					float: left;
					margin: 10px 10px 10px 0px;
				}
				.txt {
                    color:#fff;
				}
			}
			.logo-width{
				width:230px;
			}
			.logo-collapse-width{
				width:60px
			}
			.title{
                font-size: 22px;
				padding-left:20px;
				line-height: 60px;
				color:#fff;
			}
        }
    .main {
			display: flex;
			position: absolute;
			top: 60px;
			bottom: 0px;
			overflow: hidden;
			aside {
				flex:0 0 230px;
				width: 230px;
				.el-menu{
                    height: 100%;
                    /* width: 34%; */
				}
			}
			.content-container {
				flex:1;
				/* overflow-y: scroll; */
				padding: 20px;
				.breadcrumb-container {
					.title {
						width: 200px;
						float: left;
						color: #475669;
					}
					.breadcrumb-inner {
						float: right;
					}
				}
				.content-wrapper {
					background-color: #fff;
					box-sizing: border-box;
				}
			}
		}
</style>
