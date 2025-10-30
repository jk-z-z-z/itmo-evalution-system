# 接口文档（evaluationSystem）

## 全局说明

- 统一返回包裹：所有接口返回 `Result`，字段：`code`(1/0)、`msg`(`success`/`error`)、`data`(具体数据)。
- 鉴权要求：除 `/login` 与 `/login/encode` 外，所有接口必须在请求头携带 `token`（JWT）。缺失或无效将返回 HTTP 401。
- 内容类型：JSON 请求体使用 `Content-Type: application/json`；查询参数通过 URL 传递。
- 分页结构：分页接口的 `data` 为 `PageResult`，字段：`data`(列表)、`totalCount`、`currentPage`、`pageSize`、`totalPages`。

---

## 认证

### 登录
- 方法与路径：`POST /login`
- 描述：登录，返回登录信息与 `token`。
- 请求头：无需 `token`
- 请求体：
```json
{
  "username": "admin",
  "password": "123456"
}
```
- 成功响应：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 1,
    "username": "admin",
    "role": 0,
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
}
```
- 失败响应：
```json
{ "code": 0, "msg": "error", "data": null }
```

### 注册
- 方法与路径：`POST /login/register`
- 描述：注册管理员账号，成功后返回登录信息与 `token`（自动登录）。
- 请求头：无需 `token`
- 请求体：
```json
{
  "username": "admin2",
  "password": "123456"
}
```
- 成功响应：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 2,
    "username": "admin2",
    "role": 0,
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
}
```
- 失败响应（示例）：
```json
{ "code": 0, "msg": "用户名已存在", "data": null }
```

### 密码散列生成
- 方法与路径：`POST /login/encode`
- 描述：生成密码的不可逆散列（注册或调试用）。
- 请求头：无需 `token`
- 请求体：
```json
{ "password": "123456" }
```
- 成功响应（示例）：
```json
{ "code": 1, "msg": "success", "data": "$2a$10$Wus9I..." }
```

---

## 教师（/teachers）

### 新增教师
- 方法与路径：`POST /teachers`
- 请求头：`token: <JWT>`
- 请求体：
```json
{
  "name": "张三",
  "gender": 1,
  "age": 40,
  "positionId": 2,
  "titleId": 1,
  "major": 101,
  "email": "zhangsan@example.com"
}
```
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 分页查询教师
- 方法与路径：`GET /teachers`
- 请求头：`token: <JWT>`
- 查询参数：`name`（可选，模糊）、`page`（默认 1）
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "data": [
      {
        "id": 1,
        "name": "张三",
        "gender": 1,
        "age": 40,
        "positionId": 2,
        "titleId": 1,
        "major": 101,
        "email": "zhangsan@example.com"
      }
    ],
    "totalCount": 23,
    "currentPage": 1,
    "pageSize": 10,
    "totalPages": 3
  }
}
```

### 查询教师详情
- 方法与路径：`GET /teachers/edit/{id}`
- 请求头：`token: <JWT>`
- 路径参数：`id`
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 1,
    "name": "张三",
    "gender": 1,
    "age": 40,
    "positionId": 2,
    "titleId": 1,
    "major": 101,
    "email": "zhangsan@example.com"
  }
}
```

### 更新教师
- 方法与路径：`PUT /teachers/edit`
- 请求头：`token: <JWT>`
- 请求体：
```json
{
  "id": 1,
  "name": "张三",
  "gender": 1,
  "age": 41,
  "positionId": 3,
  "titleId": 1,
  "major": 101,
  "email": "zhangsan@example.com"
}
```
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 删除教师
- 方法与路径：`DELETE /teachers/{id}`
- 请求头：`token: <JWT>`
- 路径参数：`id`
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

---

## 学生（/students）

### 新增学生
- 方法与路径：`POST /students`
- 请求头：`token: <JWT>`
- 请求体：
```json
{
  "name": "李四",
  "hduId": "20200001",
  "gender": 0,
  "age": 20,
  "classId": 3001,
  "major": 201
}
```
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 分页查询学生
- 方法与路径：`GET /students`
- 请求头：`token: <JWT>`
- 查询参数：`name`（可选）、`page`（默认 1）
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "data": [
      {
        "id": 10,
        "name": "李四",
        "hduId": "20200001",
        "gender": 0,
        "age": 20,
        "classId": 3001,
        "major": 201
      }
    ],
    "totalCount": 100,
    "currentPage": 1,
    "pageSize": 10,
    "totalPages": 10
  }
}
```

### 查询学生详情
- 方法与路径：`GET /students/edit/{id}`
- 请求头：`token: <JWT>`
- 路径参数：`id`
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 10,
    "name": "李四",
    "hduId": "20200001",
    "gender": 0,
    "age": 20,
    "classId": 3001,
    "major": 201
  }
}
```

### 更新学生
- 方法与路径：`PUT /students/edit`
- 请求头：`token: <JWT>`
- 请求体：
```json
{
  "id": 10,
  "name": "李四",
  "hduId": "20200001",
  "gender": 0,
  "age": 21,
  "classId": 3001,
  "major": 201
}
```
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 删除学生
- 方法与路径：`DELETE /students/{id}`
- 请求头：`token: <JWT>`
- 路径参数：`id`
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

---

## 课程（/courses）

### 新增课程
- 方法与路径：`POST /courses`
- 请求头：`token: <JWT>`
- 请求体：
```json
{
  "name": "高等数学",
  "englishName": "Advanced Mathematics",
  "major": "数学与应用数学",
  "teacherId": 1
}
```
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 分页查询课程
- 方法与路径：`GET /courses`
- 请求头：`token: <JWT>`
- 查询参数：`name`（可选）、`page`（默认 1）
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "data": [
      {
        "id": 5,
        "name": "高等数学",
        "englishName": "Advanced Mathematics",
        "major": "数学与应用数学",
        "teacherId": 1
      }
    ],
    "totalCount": 12,
    "currentPage": 1,
    "pageSize": 10,
    "totalPages": 2
  }
}
```

### 查询课程详情
- 方法与路径：`GET /courses/edit/{id}`
- 请求头：`token: <JWT>`
- 路径参数：`id`
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 5,
    "name": "高等数学",
    "englishName": "Advanced Mathematics",
    "major": "数学与应用数学",
    "teacherId": 1
  }
}
```

### 更新课程
- 方法与路径：`PUT /courses/edit`
- 请求头：`token: <JWT>`
- 请求体：
```json
{
  "id": 5,
  "name": "高等数学（新版）",
  "englishName": "Advanced Mathematics",
  "major": "数学与应用数学",
  "teacherId": 2
}
```
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 删除课程
- 方法与路径：`DELETE /courses/{id}`
- 请求头：`token: <JWT>`
- 路径参数：`id`
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

---

## 测评（/evaluations）

### 新增测评
- 方法与路径：`POST /evaluations`
- 请求头：`token: <JWT>`
- 请求体：
```json
{
  "name": "2025 春季教学测评",
  "startDate": "2025-03-01",
  "endDate": "2025-03-31"
}
```
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 分页查询测评
- 方法与路径：`GET /evaluations`
- 请求头：`token: <JWT>`
- 查询参数：`name`（可选）、`page`（默认 1）
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "data": [
      {
        "id": 3,
        "name": "2025 春季教学测评",
        "createDate": "2025-02-20",
        "startDate": "2025-03-01",
        "endDate": "2025-03-31",
        "status": 1
      }
    ],
    "totalCount": 6,
    "currentPage": 1,
    "pageSize": 10,
    "totalPages": 1
  }
}
```

### 查询测评详情
- 方法与路径：`GET /evaluations/edit/{id}`
- 请求头：`token: <JWT>`
- 路径参数：`id`
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 3,
    "name": "2025 春季教学测评",
    "createDate": "2025-02-20",
    "startDate": "2025-03-01",
    "endDate": "2025-03-31",
    "status": 1
  }
}
```

### 测评详情（统计 + 名单）
- 方法与路径：`GET /evaluations/detail/{id}`
- 请求头：`token: <JWT>`
- 路径参数：`id`
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "evaluation": {
      "id": 3,
      "name": "2025 春季教学测评",
      "createDate": "2025-02-20",
      "startDate": "2025-03-01",
      "endDate": "2025-03-31",
      "status": 1
    },
    "evaluatedStudents": [
      {
        "id": 10,
        "name": "李四",
        "hduId": "20200001",
        "gender": 0,
        "age": 20,
        "classId": 3001,
        "major": 201
      }
    ],
    "pendingStudents": [],
    "evaluatedCount": 1,
    "pendingCount": 0,
    "totalCount": 1
  }
}
```

### 更新测评
- 方法与路径：`PUT /evaluations/edit`
- 请求头：`token: <JWT>`
- 请求体：
```json
{
  "id": 3,
  "name": "2025 春季教学测评（更新）",
  "startDate": "2025-03-05",
  "endDate": "2025-04-01"
}
```
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 删除测评
- 方法与路径：`DELETE /evaluations/{id}`
- 请求头：`token: <JWT>`
- 路径参数：`id`
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

---

## 评分汇总（/scores）

### 分页查询评分汇总
- 方法与路径：`GET /scores`
- 请求头：`token: <JWT>`
- 查询参数：`evaluationId`（默认 1）、`page`（默认 1）、`party`（默认 1）
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "data": [
      {
        "teacherName": "王五",
        "totalScore": 92.5,
        "scoreInfo": {
          "教学质量": 45.0,
          "课堂互动": 30.5,
          "作业反馈": 17.0
        }
      }
    ],
    "totalCount": 50,
    "currentPage": 1,
    "pageSize": 10,
    "totalPages": 5
  }
}
```

---

## 图表统计（/charts）

### 基础统计信息
- 方法与路径：`GET /charts`
- 请求头：`token: <JWT>`
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "chinaTeacherGenderInfo": { "男": 120, "女": 80 },
    "russiaTeacherGenderInfo": { "男": 60, "女": 55 },
    "chinaTeacherTitleInfo": { "教授": 30, "副教授": 50, "助教": 40 },
    "russiaTeacherTitleInfo": { "教授": 20, "副教授": 40, "助教": 55 }
  }
}
```

### 教师得分详情（雷达图）
- 方法与路径：`GET /charts/teacher/detail`
- 请求头：`token: <JWT>`
- 查询参数：`teacherId`、`party`、`evaluationId`
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "教学质量": 45.0,
    "课堂互动": 30.5,
    "作业反馈": 17.0
  }
}
```

### 教师总分预测
- 方法与路径：`GET /charts/teacher/totalScore/prediction`
- 请求头：`token: <JWT>`
- 查询参数：`teacherId`
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "教学质量": 46.0,
    "课堂互动": 31.0,
    "作业反馈": 18.0
  }
}
```

### 教师排名 TOP10
- 方法与路径：`GET /charts/teacher/rank`
- 请求头：`token: <JWT>`
- 查询参数：`party`、`evaluationId`
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "teacherName": "王五",
      "totalScore": 92.5,
      "scoreInfo": { "教学质量": 45.0, "课堂互动": 30.5, "作业反馈": 17.0 }
    }
  ]
}
```

---

## 意见反馈（/opinions）

### 新增意见反馈
- 方法与路径：`POST /opinions`
- 请求头：`token: <JWT>`
- 请求体：
```json
{
  "evaluationId": 3,
  "sendName": "学生A",
  "sendTime": "2025-03-10",
  "teacherId": 1,
  "content": "课堂互动很好"
}
```
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 获取意见列表与教师名单
- 方法与路径：`GET /opinions`
- 请求头：`token: <JWT>`
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "CteacherList": ["张三", "李四"],
    "RteacherList": ["Ivan", "Vladimir"],
    "OpinionList": [
      { "sendTime": "2025-03-10", "sendName": "学生A", "teacherId": 1 }
    ]
  }
}
```

---

## 测评体系（/systems）

### 按阵营获取体系
- 方法与路径：`GET /systems`
- 请求头：`token: <JWT>`
- 查询参数：`party`
- 成功响应（示例）：
```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 100,
      "headName": "教学质量",
      "proportion": 0.5,
      "party": 0,
      "evaluationSystemBodyVoList": [
        { "id": 1001, "bodyName": "课堂组织" },
        { "id": 1002, "bodyName": "课程难度" }
      ]
    }
  ]
}
```

### 新增体系（头 + 体）
- 方法与路径：`POST /systems/edit`
- 请求头：`token: <JWT>`
- 请求体：
```json
{
  "headName": "教学质量",
  "proportion": 0.5,
  "party": 0,
  "evaluationSystemBodyList": [
    { "bodyName": "课堂组织" },
    { "bodyName": "课程难度" }
  ]
}
```
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 删除 head
- 方法与路径：`DELETE /systems/edit/head`
- 请求头：`token: <JWT>`
- 查询参数：`headId`
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 删除 body
- 方法与路径：`DELETE /systems/edit/body`
- 请求头：`token: <JWT>`
- 查询参数：`bodyId`
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 更新 head
- 方法与路径：`PUT /systems/edit/head`
- 请求头：`token: <JWT>`
- 请求体：
```json
{
  "id": 100,
  "headName": "教学质量（更新）",
  "proportion": 0.6
}
```
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

### 更新 body
- 方法与路径：`PUT /systems/edit/body`
- 请求头：`token: <JWT>`
- 请求体：
```json
{
  "id": 1001,
  "bodyName": "课堂组织（更新）"
}
```
- 响应：
```json
{ "code": 1, "msg": "success", "data": null }
```

---

## 错误与状态
- 成功：`{ "code": 1, "msg": "success", "data": ... }`
- 业务失败：`{ "code": 0, "msg": "error" }` 或 `{"code":0,"msg":"<错误信息>"}`
- 未授权：HTTP 401（缺少或无效 `token`）