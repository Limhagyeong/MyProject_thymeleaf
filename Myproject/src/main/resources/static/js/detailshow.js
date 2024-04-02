    let detail=Vue.createApp({
    	methods:{
    		subImgShow(){
   			 const imageContainer = document.getElementById('image-container');
                const subImgButton = this.$refs.subImg

                if (this.isShow===false) {
                    imageContainer.style.height = "auto"
                    subImgButton.value = "상세보기접기"
                    this.isShow=true
                } else {
                    imageContainer.style.height = "500px"
                    subImgButton.value = "상품상세보기"
                    this.isShow=false
                }
               
   		}
    	}
    }).mount("#detail")