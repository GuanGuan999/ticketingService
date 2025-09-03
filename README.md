# Summary
## Spring Data JPA
- 继承 JpaRepository自动拥有基本的 CRUD 方法（save(), findById(), findAll(), deleteById()）
- 自定义方法名规则 : findBy, existsBy, deleteBy, removeBy, And, LessThan ...
- JpaRepository<User, Long> ： 第一个参数（实体类）， 第二个参数（主键类型）
## Spring Security
- 导入包：implementation("org.springframework.boot:spring-boot-starter-security")
         testImplementation("org.springframework.security:spring-security-test")
- 密码加密工具类： BCryptPasswordEncoder
- HTTP 安全策略 ： SecurityFilterChain
  1. 哪些请求 不需要登录（permitAll）
  2. 哪些请求 必须认证（authenticated）
  3. 是否开启 CSRF、防火墙、Session 管理等
     CSRF ： 是一种网络攻击手段，攻击者利用用户已经登录的网站身份，诱导用户浏览器发送 伪造请求。 禁用场景 ： API / Token
## ModelMapper
- 实体类 ↔ DTO转换
## JWT验证
- 导入包 ： jjwt-api， jjwt-impl， jjwt-jackson
- 生成JWT
  - 例子 : Jwts.builder()
                .setSubject(email)  // 设置 JWT 的主题 存放用户唯一标识
                .setIssuedAt(new Date()) // 设置 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) // 设置 过期时间
                .signWith(SignatureAlgorithm.HS256, key()) // 签名算法和密钥 HS256 → HMAC-SHA256 对称加密算法
                .compact(); // 将 Header + Payload + Signature 合成最终 JWT 字符串
- 验证Token : validateToken
  - 例子 ： Jwts.parser() // 创建一个 JWT 解析器（Parser）对象
               .setSigningKey(key()) // 设置 签名密钥
               .parseClaimsJws(token) // 解码 Header 和 Payload 校验签名
               .getBody(); // 从 Jws<Claims> 中获取 Payload 部分（业务信息）
    返回Claims对象
    Claims里存放
       .getSubject() → sub
       .getExpiration() → exp
       .get("role") → 自定义字段
- secret key和过期时间
  1. @Value("${jwt.secret}")， @Value("${jwt.expiration-ms}")通过@Value注入
    
