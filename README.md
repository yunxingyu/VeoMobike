# VeoMobike
Veo 共享电动车项目，项目基于Compose及jetpack完成的组件化应用，内置了google地图，需要使用google player service。
1、项目采用了最新的jetpack开发规范，使用Hilt完成注入框架引入，封装了common，集成了okhttp、retrofit及异步封装， 满足通用的网络功能接口、Html展示组件、日志及数据库等公共功能与工具封装、map_module公共地图接口抽象，满足不同国家与地区地图功能切换；
2、项目采用kotlin (DSL)取代传统gradle语言的项目配置语言；
3、三方库版本管理，使用一线的toml语言，项目配置，依据构建功能与作用，在build_logic中依次实现，方便统一的构建代码管理；
4、项目基础框架支持mvvm数据驱动业务及MVI通过Intent（事件）驱动；
5、内置了GMS、Google stroe 及Google地图功能接入;
6、分别基于原生mapView方式及直接maps-compose地图组件实现地图功能，满足未来不同场景对地图功能需要。
工程基于最新的android开发规范与环境，采用声明式布局实现整个应用的多语言、多字体、多风格及组件化需求，模块直接采用navigation通信，功能模块间及组件之间使用livadata、Hilt完成数据与UI、功能与功能之间解耦分离。
