# Bank Management System (Full-Stack Dockerized)

這是一個基於 **Spring Boot 3** 與 **Vue 3** 開發的銀行金融商品管理系統。本專案實現了完整的前後端分離架構，並透過 **Docker Compose** 達成一鍵式環境部署與資料庫自動初始化。

## 技術棧 (Tech Stack)

* **Backend:** Java 17, Spring Boot 3, Spring Data JPA, Hibernate
* **Frontend:** Vue 3 (Vite), Axios, HTML5/CSS3
* **Database:** MySQL 8.0 (部署於 Docker 容器)
* **DevOps:** Docker, Docker Compose, Git

## 核心功能

  金融商品展示: 從 MySQL 資料庫動態讀取產品名稱、類別、價格及手續費。
  喜好清單系統: 透過資料庫預儲程序 (Stored Procedure) 處理金融計算（如總手續費與總金額）。
  容器化架構: 包含資料庫自動初始化 (`ddl.sql`, `dml.sql`)、後端 API 服務與前端 Web 服務的完整鏈接。


請確保您的電腦已安裝 Docker Desktop。

1.  **複製專案**:
    ```bash
    git clone https://github.com/peechi777/bank-system
    cd bank-system
    ```

2.  **一鍵啟動 (包含自動編譯與環境建置)**:
    ```bash
    docker-compose up -d --build
    ```

3.  **訪問服務**:
    * **前端介面**: `http://localhost:5173`
    * **後端 API**: `http://localhost:8081/api/products`
    * **資料庫埠號**: `8806` (映射至容器內 3306)

--------------------------------------------------------------------
一、 核心功能模組 (Business Logic)
建立喜好項目: 支援用戶新增自定義金融商品（含名稱、價格、費率）、扣款帳號及申購數量。
多維度清單查詢: 提供整合視圖，展示商品清單、扣款帳號、試算數據（預計扣款總額、總手續費）及用戶聯繫信箱。
資訊維護與變更: 提供完整的資料異動介面，支援針對商品基本資訊、帳號及數量的修改與刪除。

二、 系統架構規範 (System Architecture)
三層式部署: 採用 Web Server / Application Server / RDBMS 獨立分層。
後端分層架構: 嚴格劃分 Presentation (展示層)、Business (業務層)、Data Access (資料層) 與 Common (共用層)。

三、 關鍵技術指標 (Technical Requirements)
開發棧:
Frontend: Vue.js 框架。
Backend: Spring Boot 框架 (基於 Maven/Gradle 管理)。
Protocol: 遵循 RESTful API 設計規範。

資料庫操作與封裝:
數據存取: 強制透過 Stored Procedure (SP) 進行資料庫讀寫。
事務管理: 涉及多表操作時必須實作 Transaction 以確保 ACID 特性。
交付規範: 所有的 DDL 與 DML 腳本必須統一存放於專案根目錄之 /DB 資料夾。

資安防護:
實作參數化查詢或 SP 以防止 SQL Injection。
實作資料清洗與轉義機制以防止 XSS 攻擊。

https://github.com/user-attachments/assets/4030b253-b37c-48bf-8716-ec9b45496d48
