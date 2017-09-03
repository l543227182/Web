function createText() {
	var isChecked = document.getElementById("selectPermission");
	var temp = document.getElementById("divTable");
	if (isChecked.checked) {
		temp.style.display = "block";
	} else {
		temp.style.display = "none";
	}
}

function isNull(str) {
	if (str == "") {
		return true;
	}
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}


function selectSex() {
	var female = document.getElementById("female");
	female.checked = "checked";
}

function selectRole() {
	var tempRole = document.getElementById("tempRole").value;
	var role = document.getElementById("role");
	var options = role.options;
	for (var i = 0; i < options.length; i++) {
		if (options[i].value == tempRole) {
			options[i].selected = "selected";
			return;
		}
	}
}

function selectProduct() {
	var tempProduct = document.getElementById("tempProduct").value;
	var product = document.getElementById("productId");
	var options = product.options;
	for (var i = 0; i < options.length; i++) {
		if (options[i].value == tempProduct) {
			options[i].selected = "selected";
			return;
		}
	}
}

function selectProductType() {
	var tempType = document.getElementById("tempProductType").value;
	var productType = document.getElementById("productType");
	var options = productType.options;
	for (var i = 0; i < options.length; i++) {
		if (options[i].value == tempType) {
			options[i].selected = "selected";
			return;
		}
	}
}

function selectAll(){
	var permissions = document.getElementsByName("addPermission");
	
	for( var i=0; i<permissions.length; i++){
		permissions[i].checked = true;
	}
}
function clearAll(){
	var permissions = document.getElementsByName("addPermission");
	
	for( var i=0; i<permissions.length; i++){
		permissions[i].checked = false;
	}
}

function selectPermission(){
	var pid = document.getElementById("pid").value;
	var permissions = document.getElementsByName("addPermission");
	var num = log2(pid);
	if (isNumber(num)){
		for( var i=0; i<permissions.length; i++){
			if(permissions[i].value==num){
				permissions[i].checked = true;
			}	
		}		
	}else{
	
		var total=0;
		var n=10000;
		var count=0;
	
		for(var i=0;i<=n;i++){
			total=total+Math.pow(2,i);
			if(total>pid){
				count=i-1;
				break;
			}	
		}
			
		for( var i=0; i<permissions.length; i++){
			for( var j=0; j<=count ; j++){
				if(permissions[i].value==Math.pow(2,j)){					
					permissions[i].checked = true;
				}	
			}		
		}
	}	
}

function log2(x){
	return Math.LOG2E * Math.log(x);
}
/*****************************************
功能：检查是否为Email Address
参数：inputString    要检查的字符串
返回值：
	true ：合法的mail地址
	false：非法Email地址
******************************************/
function isEmail(inputString) {
	var pattern = /^([\.a-zA-Z0-9_-]){3,}@([a-zA-Z0-9_-]){1,}(\.([a-zA-Z0-9]){1,}){1,}$/;
	if (!pattern.test(inputString)) {
		return false;
	}
	return true;
}
/*****************************************
功能：检查是否为整数数字
参数：str    要检查的字符串
返回值：true为是数字，false为不是数字
******************************************/
function isNumber(str) {
	return !/\D/.test(str);
}

/*****************************************
功能：检查是否为电话号码
参数：要检查的字符串
返回值：true为是合法，false为不合法
*****************************************/
function isPhone(phone) {
	var i, j, strTemp;
	strTemp = "0123456789-()# ";
	for (i = 0; i < phone.length; i++) {
		j = strTemp.indexOf(phone.charAt(i));
		if (j == -1) {
			//说明有字符不合法
			return false;
		}
	}
	//说明合法
	return true;
}

