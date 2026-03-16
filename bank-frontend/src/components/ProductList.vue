<template>
  <div class="product-container">
    <h2>銀行金融商品展示</h2>
    <hr />
    
    <div v-for="product in products" :key="product.id" class="product-item">
      <h3>{{ product.name }}</h3>
      <p>分類：{{ product.category }}</p>
      <p>價格：${{ product.price?.toLocaleString() || 0 }}</p>
      <p>手續費率：{{ (product.feeRate * 100).toFixed(1) }}%</p>
      <p>描述：{{ product.description }}</p>
      
      <button @click="addToLikeList(product)" class="buy-btn">加入收藏</button>
      <hr />
    </div>

    <div class="favorite-list-container" style="margin-top: 50px;">
    <h3>我的喜好金融商品清單 (使用者：{{ userInfo.userName }})</h3>
      <table class="fav-table">
        <thead>
          <tr>
            <th>產品名稱</th>
            <th>數量</th>
            <th>扣款帳號</th>
            <th>總手續費</th>
            <th>總金額</th>
            <th>聯絡 Email</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="fav in favoriteList" :key="fav.sn">
            <td>{{ fav.productName }}</td>
            <td>{{ fav.orderQty }}</td>
            <td>{{ fav.account }}</td>
            <td>${{ fav.totalFee.toLocaleString() }}</td>
            <td class="total-amount">${{ fav.totalAmount.toLocaleString() }}</td>
            <td>{{ fav.email }}</td>
            <td>
              <button @click="editFavorite(fav)" class="edit-btn">修改</button>
              <button @click="deleteFavorite(fav.sn)" class="del-btn">刪除</button>
            </td>
          </tr>
          <tr v-if="favoriteList.length === 0">
            <td colspan="7" style="text-align: center;">尚無清單，快去收藏吧！</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';


const products = ref([]);
const favoriteList = ref([]);
const userId = 'A1236456789'; 


const fetchProducts = async () => {
  try {
    const response = await axios.get('http://localhost:8081/api/products');
    products.value = response.data;
  } catch (error) {
    console.error("抓取產品失敗:", error);
  }
};

const fetchFavorites = async () => {
  try {
    const response = await axios.get(`http://localhost:8081/api/like-list/${userId}`);
    favoriteList.value = response.data;
  } catch (error) {
    console.error("抓取清單失敗:", error);
  }
};

const addToLikeList = async (product) => {
  const qty = prompt("請輸入欲購買數量", "1");
  const account = prompt("請輸入扣款帳號", "1111999666");

  if (qty && account) {
    try {
      const response = await axios.post('http://localhost:8081/api/like-list/add', null, {
        params: {
          userId: userId, 
          productId: product.id,
          qty: parseInt(qty),
          account: account
        }
      });
      if (response.data === "Success") {
        alert("加入成功！");
        await fetchFavorites(); 
      }
    } catch (error) {
      alert("加入失敗，請檢查後端連線");
    }
  }
};

const deleteFavorite = async (sn) => {
  if (confirm("確定要刪除這筆收藏嗎？")) {
    console.log("準備刪除序號:", sn);
    
    try {

      const response = await axios.delete(`http://localhost:8081/api/like-list/${sn}`);
      
      if (response.status === 200) {
        alert("已成功刪除！");
        await fetchFavorites(); 
      }
    } catch (error) {
      console.error("刪除失敗:", error);
      alert("刪除失敗，請檢查後端連線或控制台錯誤訊息");
    }
  }
};

onMounted(() => {
    console.log("...");
    fetchProducts();   
    fetchFavorites();  
});

const editFavorite = async (fav) => {
  const newQty = prompt("請輸入新的購買數量", fav.orderQty);
  const newAccount = prompt("請輸入新的扣款帳號", fav.account);

  if (newQty && newAccount) {
    try {
      const response = await axios.put(`http://localhost:8081/api/like-list/${fav.sn}`, null, {
        params: {
          qty: parseInt(newQty),
          account: newAccount
        }
      });

      if (response.data === "Update Success") {
        alert("修改成功！");
        await fetchFavorites(); 
      }
    } catch (error) {
      console.error("修改失敗:", error);
      alert("修改失敗，請檢查網路連線");
    }
  }
};

// user info
const userInfo = ref({ userName: '', email: '' });

// 抓取
const fetchUserInfo = async () => {
  try {
    const response = await axios.get(`http://localhost:8081/api/users/${userId}`);
    userInfo.value = response.data;
  } catch (error) {
    console.error("抓取使用者失敗:", error);
  }
};


onMounted(() => {
  fetchProducts();
  fetchFavorites();
  fetchUserInfo(); 
});

</script>

<style scoped>
.product-container { padding: 20px; font-family: Arial, sans-serif; }
.product-item { margin-bottom: 20px; text-align: left; }
.buy-btn { background-color: #00a887; color: white; padding: 8px 16px; border: none; cursor: pointer; border-radius: 4px; }
.buy-btn:hover { background-color: #008f72; }

.fav-table { width: 100%; border-collapse: collapse; margin-top: 20px; }
.fav-table th, .fav-table td { border: 1px solid #ddd; padding: 12px; text-align: left; }
.fav-table th { background-color: #f4f4f4; }
.total-amount { color: #d9534f; font-weight: bold; }

.edit-btn { 
  background-color: #1890ff; 
  color: white; 
  border: none; 
  padding: 5px 10px; 
  margin-right: 5px; 
  cursor: pointer; 
  border-radius: 4px; 
}
.edit-btn:hover { background-color: #40a9ff; }
</style>