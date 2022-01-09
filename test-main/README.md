# 專案總架構表

## 賣家商品管理頁面

### 新增商品:
newProduct.html --> ProductServlet

### 列出所有商品內容:
ListAllProductServlet --> displayProducts.jsp


### 個別商品查詢與指定欄位查詢
ShowImageService 取出資料方法要修改為只取出圖片


### 更新商品
1. 由ListAllProductServlet進入至displayProducts.jsp列出所有商品內容
2. 加上個別商品的修改連結並將商品ID傳送到EditProductServlet，
3. 取出資料，並把資料傳入value中forward至editProduct.jsp中。
4. 完成編輯送出後，將資料送到UpdateProductServlet中完成更新


### 刪除商品
1. 由ListAllProductServlet進入至displayProducts.jsp列出所有商品內容
2. 加入刪除連結將欲刪除的商品傳送到DeleteProductServlet執行刪除
3. 加入javascript 提示視窗，再次確認是否要刪除
4. editProduct.jsp已經加入刪除功能
displayProducts.jsp已經加入刪除提示功能，但重新導向ListAllProductServlet
仍然抓的到被刪除商品的資訊，但找不到圖檔



## 首頁
1. HomePageServlet -> homePage.jsp

## 網頁基礎模板(持續更新)
WEB-INF/view/skel.jsp

## 個別商品頁面
1. HomePageServlet -> ProductInfoServlet -> productInfo.jsp

## 購物車功能
購物車類別: ShoppingCart, Service: ShoppingCartService
1. productInfo.jsp -> 直接購買 -> ShoppingCartServlet -> shoppingCart.jsp
2. 如果購物車沒商品，不論是刪除或初次瀏覽購物車，將導向emptyCart.jsp
3. 尚未完成shoppingCart.jsp 網頁渲染

## 登入接口
homePage.jsp -> LoginServlet 登入/登出


## 搜尋bar component
使用者輸入資訊、進行商品搜尋
實作ProductServiceImpl.searchProduct(String[] strs)僅針對指定關鍵字陣列進行搜尋(未針對錯別字列出可能結果)
navbarSearchCompnent -> SearchProductServlet -> searchProduct.jsp (尚未完成網頁渲染)

## 結帳
shoppingCart.jsp點選結帳 -> CheckoutServlet 
-> 呼叫CheckoutServiceImpl.checkout() 
	1. 更新product table、
	2. 存入消費者訂單table(orders、order_items)、
	3. 廠商訂單table(seller_order、seller_order_items)
結帳成功 ? 返回首頁 : 返回購物車頁面


## 整合賣家管理頁面
首頁 -> 賣家登入頁面(SellerLoginServlet -> login ? : sellerLoginPage.jsp) -> 賣家管理頁面 sellerManage.jsp
賣家管理頁面: 
1. 基本資料管理 
2. 訂單管理 sellerManage.jsp -> SellerOrderManageServlet -> sellerOrderManage.jsp
3. 商品管理: ProductManageServlet
sellerManage.jsp -> 

SellerProductCreateServlet -> productManageCreate.jsp (新增), 
SellerProductUpdateServlet -> productManageUpdate.jsp (修改與刪除), 
SellerReadServlet -> productManageRead.jsp (瀏覽與刪除)


Note:
1. 商品管理更新內容:

	1. 瀏覽: sellerManage.jsp (read) -> SellerProductReadServlet -> productManageRead.jsp
	2. 編輯商品: 
	* sellerManage.jsp (update) -> SellerProductUpdateServlet -> productManageUpdate.jsp
	* productManageRead.jsp 點選個別商品操作按鈕 -> SellerProductUpdateServlet -> productManageUpdate.jsp





