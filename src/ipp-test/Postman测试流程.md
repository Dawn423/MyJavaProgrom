# IPP Resource Field Mapper — Postman 完整测试流程

基于 **original_str**、**standard_str**、**field** 的增删改查。默认 base URL：`http://localhost:8080`（确保应用已启动）。

---

## 一、查（Query）

### 1. 查询全部（未删除）

- **方法**: `GET`
- **URL**: `http://localhost:8080/api/ipp/resource-field-mapper/all`
- **参数**: 无
- **说明**: 返回所有 `is_delete = 0` 的记录。

---

### 2. 按 id 查询单条

- **方法**: `GET`
- **URL**: `http://localhost:8080/api/ipp/resource-field-mapper/1`
- **路径参数**: 将 `1` 换成实际 id。
- **说明**: 按主键查一条（含已软删记录）。

---

### 3. 按 field 查询

- **方法**: `GET`
- **URL**: `http://localhost:8080/api/ipp/resource-field-mapper/field/1`
- **路径参数**: 将 `1` 换成要查的 `field` 值（如 1 或 2）。
- **说明**: 返回该 field 下未删除的记录。

---

### 4. 按 original_str 查询

- **方法**: `GET`
- **URL**: `http://localhost:8080/api/ipp/resource-field-mapper/by-original?originalStr=公分`
- **Query 参数**: `originalStr` = 原始字符串（如：毫米、米、公分、平方米）。
- **说明**: 返回该原始字符串对应的未删除记录（可能多条）。

**Postman 操作**: Params 里添加 Key: `originalStr`, Value: `公分`。

---

### 5. 按 standard_str 查询

- **方法**: `GET`
- **URL**: `http://localhost:8080/api/ipp/resource-field-mapper/by-standard?standardStr=cm`
- **Query 参数**: `standardStr` = 标准字符串（如：mm、m、cm、m2）。
- **说明**: 返回该标准字符串对应的未删除记录。

**Postman 操作**: Params 里添加 Key: `standardStr`, Value: `cm`。

---

## 二、增（Create）

### 6. 新增一条（必填：original_str、standard_str、field）

- **方法**: `POST`
- **URL**: `http://localhost:8080/api/ipp/resource-field-mapper`
- **Headers**: `Content-Type: application/json`
- **Body**（raw JSON）:

```json
{
  "originalStr": "公顷",
  "standardStr": "ha",
  "multiple": 1,
  "field": 1
}
```

- **参数说明**:
  - `originalStr`（必填）: 原始字符串
  - `standardStr`（必填）: 标准字符串
  - `field`（必填）: 字段，如 1 或 2
  - `multiple`（可选）: 倍数，默认 1
  - `userName`（可选）: 用户名

- **成功**: 响应为受影响行数（如 `1`）。之后可用「查全部」或「按 originalStr/standardStr/field 查」验证。

---

## 三、改（Update）

### 7. 全量更新（PUT）

- **方法**: `PUT`
- **URL**: `http://localhost:8080/api/ipp/resource-field-mapper`
- **Headers**: `Content-Type: application/json`
- **Body**（raw JSON）:

```json
{
  "id": 1,
  "originalStr": "公顷",
  "standardStr": "ha",
  "multiple": 1,
  "field": 2,
  "userName": "admin"
}
```

- **说明**: 必须带 `id`；会整体更新该条记录的 original_str、standard_str、field 等。先通过「按 id / 按 originalStr / 按 field 查」拿到 id 再更新。

---

### 8. 部分更新（PATCH）

- **方法**: `PATCH`
- **URL**: `http://localhost:8080/api/ipp/resource-field-mapper`
- **Headers**: `Content-Type: application/json`
- **Body**（raw JSON，只传要改的字段 + id）:

```json
{
  "id": 1,
  "originalStr": "平方千米",
  "standardStr": "km2"
}
```

- **说明**: 只更新提供的字段（如只改 original_str、standard_str 或 field），其它保持不变。

---

## 四、删（Delete，物理删除）

### 9. 按 id 物理删除

- **方法**: `DELETE`
- **URL**: `http://localhost:8080/api/ipp/resource-field-mapper/1`
- **路径参数**: 将 `1` 换成要删记录的 id。
- **说明**: 物理删除，删除后会自动调整后续记录的 id 以保持连续，并重置自增序列。

### 10. 按条件删除

- **方法**: `DELETE`
- **URL**: `http://localhost:8080/api/ipp/resource-field-mapper/by-condition?originalStr=公顷&standardStr=ha&field=1`
- **Query 参数**: `originalStr`、`standardStr`、`field`
- **说明**: 通过 originalStr + standardStr + field 组合条件删除记录，并保持 id 连续。

**建议流程**: 先用「按 originalStr 或 standardStr 或 field 查」得到 id，再调用本接口删除。

---

## 五、推荐测试顺序（一条龙）

1. **查全部** — GET `/api/ipp/resource-field-mapper/all`，确认初始数据。
2. **增** — POST 一条（如 originalStr=公顷, standardStr=ha, field=1）。
3. **按 original_str 查** — GET `/api/ipp/resource-field-mapper/by-original?originalStr=公顷`，确认新增。
4. **按 standard_str 查** — GET `/api/ipp/resource-field-mapper/by-standard?standardStr=ha`。
5. **按 field 查** — GET `/api/ipp/resource-field-mapper/field/1`。
6. **改** — 从响应中取 `id`，用 PUT 或 PATCH 修改 originalStr/standardStr/field，再按上面任一种查验证。
7. **删** — DELETE `/api/ipp/resource-field-mapper/{id}`，再查全部或按 originalStr 确认已不可见。

---

## 六、接口速查表

| 操作 | 方法 | URL 或参数 |
|------|------|------------|
| 查全部 | GET | `/api/ipp/resource-field-mapper/all` |
| 按 id 查 | GET | `/api/ipp/resource-field-mapper/{id}` |
| 按 field 查 | GET | `/api/ipp/resource-field-mapper/field/{field}` |
| 按 original_str 查 | GET | `/api/ipp/resource-field-mapper/by-original?originalStr=值` |
| 按 standard_str 查 | GET | `/api/ipp/resource-field-mapper/by-standard?standardStr=值` |
| 新增 | POST | Body: originalStr, standardStr, field（及可选 multiple, userName） |
| 全量更新 | PUT | Body: id + originalStr, standardStr, field 等 |
| 部分更新 | PATCH | Body: id + 要改的 originalStr/standardStr/field |
| 按 id 物理删除 | DELETE | `/api/ipp/resource-field-mapper/{id}` |
| 按条件删除 | DELETE | `/api/ipp/resource-field-mapper/by-condition?originalStr=值&standardStr=值&field=值` |

---

可直接在 Postman 中按上述请求逐个创建，或导入项目中的 **`IPP-Resource-Field-Mapper.postman_collection.json`** 使用现成集合。
