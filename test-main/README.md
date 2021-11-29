# 賣家商品管理頁面

## 新增商品:
newProduct.html --> ProductServlet

## 列出所有商品內容:
ListAllProductServlet --> displayProducts.jsp


## 個別商品查詢與指定欄位查詢
ShowImageService 取出資料方法要修改為只取出圖片


## 更新商品
1. 由ListAllProductServlet進入至displayProducts.jsp列出所有商品內容
2. 加上個別商品的修改連結並將商品ID傳送到EditProductServlet，
3. 取出資料，並把資料傳入value中forward至editProduct.jsp中。
4. 完成編輯送出後，將資料送到UpdateProductServlet中完成更新


## 刪除商品
1. 由ListAllProductServlet進入至displayProducts.jsp列出所有商品內容
2. 加入刪除連結將欲刪除的商品傳送到DeleteProductServlet執行刪除
3. 加入javascript 提示視窗，再次確認是否要刪除
editProduct.jsp 一樣要加入有刪除功能