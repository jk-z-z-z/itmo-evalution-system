# 教学评价系统 API 文档

## 概览
- 基础 URL：`http://localhost:8080`（可通过 `server.port` 修改）
- 技术栈：Spring Boot 3 + MyBatis + MySQL
- 统一响应：`Result { code, msg, data }`
- 分页响应：`PageResult { total, records }`

## 完整性检查结论
- 登录/认证：未实现。项目中不存在登录接口（如 `/auth/login`）、权限控制或安全配置（`SecurityFilterChain`）。
- 控制器覆盖：学生、教师、课程、评价、评价体系、评分、图表均有接口；意见模块的控制器方法缺少 HTTP 映射注解，当前不可访问（见下）。

---

## 学生管理（Students）
- 基路径：`/students`
- 接口：
  - `POST /students` 添加学生
  - `GET /students?name=&page=` 分页查询学生（姓名模糊）
  - `GET /students/edit/{id}` 按 ID 查询
  - `PUT /students/edit` 更新学生
  - `DELETE /students/{id}` 删除学生

请求示例（添加）：
```json
{
  "name": "张三",
  "hduId": "21051001",
  "gender": 1,
  "age": 20,
  "classId": 1,
  "major": 1
}
```

---

## 教师管理（Teachers）
- 基路径：`/teachers`
- 接口：
  - `POST /teachers` 添加教师
  - `GET /teachers?name=&page=` 分页查询教师（姓名模糊）
  - `GET /teachers/edit/{id}` 按 ID 查询
  - `PUT /teachers/edit` 更新教师
  - `DELETE /teachers/{id}` 删除教师

---

## 课程管理（Courses）
- 基路径：`/courses`
- 接口：
  - `POST /courses` 添加课程
  - `GET /courses?name=&page=` 分页查询课程（名称模糊）
  - `GET /courses/edit/{id}` 按 ID 查询
  - `PUT /courses/edit` 更新课程
  - `DELETE /courses/{id}` 删除课程

---

## 评价管理（Evaluations）
- 基路径：`/evaluations`
- 接口：
  - `POST /evaluations` 创建评价
  - `GET /evaluations?name=&page=` 分页查询评价
  - `GET /evaluations/edit/{id}` 按 ID 查询
  - `GET /evaluations/detail/{id}` 评价详情（统计 + 名单）
  - `PUT /evaluations/edit` 更新评价
  - `DELETE /evaluations/{id}` 删除评价

---

## 评价体系（Evaluation Systems）
- 基路径：`/systems`
- 接口：
  - `GET /systems?party=` 按参与方查询评价体系
  - `POST /systems/edit` 添加评价体系
  - `DELETE /systems/edit/head?headId=` 删除体系头部
  - `DELETE /systems/edit/body?bodyId=` 删除体系子项
  - `PUT /systems/edit/head` 更新体系头部
  - `PUT /systems/edit/body` 更新体系子项

---

## 评分（Scores）
- 基路径：`/scores`
- 接口：
  - `GET /scores?evaluationId=&page=&party=` 分页查询评分明细

响应数据结构：`PageResult<List<teacherScoreDetailVo>>`

---

## 图表（Charts）
- 基路径：`/charts`
- 接口：
  - `GET /charts` 获取基础统计信息
  - `GET /charts/teacher/detail?teacherId=&party=&evaluationId=` 教师雷达图明细
  - `GET /charts/teacher/totalScore/prediction?teacherId=` 教师总分预测表
  - `GET /charts/teacher/rank?party=&evaluationId=` 教师评分排名

---

## 意见（Opinions）
- 基路径：`/opinions`
- 当前实现：控制器包含 `add(OpinionAddRequest)` 与 `getList()` 方法，但缺少 `@PostMapping`、`@GetMapping` 等注解，暂不可通过 HTTP 访问。
- 建议修复：
  - 为 `add` 方法添加 `@PostMapping`
  - 为 `getList` 方法添加 `@GetMapping`

---

## 通用返回格式
`Result`
```json
{
  "code": 1,
  "msg": "success",
  "data": {}
}
```
`PageResult`
```json
{
  "total": 100,
  "records": [
    {}
  ]
}
```

## 重要说明
- 登录/权限：项目暂未集成认证与鉴权。可选方案：Spring Security + JWT；新增 `/auth/login`、`/auth/logout`、`/auth/refresh` 等接口，并在受保护资源上配置拦截。
- 端口与配置：默认端口为 8080，`application.yml` 可配置数据库等信息。