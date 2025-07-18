# mk-gpt

此仓库包含一个示例 IntelliJ 插件以及一个简单的前后端示例项目。

## 虚拟宠物应用

- `pet-server` 目录下是使用 **Spring Boot** 编写的后端，保存宠物状态并提供 REST 接口。
- `pet-web` 目录包含独立的 **Vue** 前端，需要单独启动或直接在浏览器中打开。

### 运行方式

1. 在项目根目录执行：
   ```bash
   mvn -pl pet-server spring-boot:run
   ```
2. 打开浏览器访问 pet-web/index.html 即可开始与宠物互动（确保后端已启动）。
