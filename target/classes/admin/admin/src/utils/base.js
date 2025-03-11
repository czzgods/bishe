const base = {
    get() {
        return {
            url : "http://localhost:8080/springboot798884g4/",
            name: "springboot798884g4",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboot798884g4/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于springboot的少儿编程教育机构管理系统"
        } 
    }
}
export default base
