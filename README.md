# mk-gpt

此仓库包含一个示例 IntelliJ 插件以及一个简单的前后端示例项目。

## 虚拟宠物应用

- `pet-server` 目录下是使用 **Spring Boot** 编写的后端，保存宠物状态并提供 REST 接口，并且内置了前端页面。
- 原来的 `pet-web` 目录保留了前端源码，构建时会被复制到 `pet-server` 的 `static` 目录。

### 运行方式

1. 在项目根目录执行：
   ```bash
   mvn -pl pet-server spring-boot:run
   ```
2. 打开浏览器访问 [http://localhost:8080](http://localhost:8080) 即可开始与宠物互动。
