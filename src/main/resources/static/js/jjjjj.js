console.log("jjjjj.js");

/* $.ajax(), $.get(), $.post 중에 골라서 사용하면 된다. */
// DB 가져오기
$(document).ready(function (){
    getAllRestaurants()
});

// GET
function getAllRestaurants(){
    $.get({
        url: "http://43.200.83.7:80/get-all-restaurants",
        dataType: "json",
        success: function (res) {
            // 화면에 반영
            var listOfRestaurants = "";
            res.forEach(restaurant => {
                var restaurant
                    = "<tr>"
                    + "<td><span>"
                    + restaurant.id
                    + "</span></td>"
                    + "<td><span>"
                    + restaurant.name
                    + "</span></td>"
                    + "<td><span>"
                    + restaurant.address
                    + "</span></td>"
                    + "<td><span>"
                    + restaurant.starPoint
                    + "</span></td>"
                    + "<td><span>"
                    + restaurant.comment
                    + "</span></td>"
                    + "</tr>";

                listOfRestaurants = listOfRestaurants.concat(restaurant);
            });

            $("#listOfRestaurants").html(listOfRestaurants) ;
        },
        error : function () {
            alert("error : getAllRestaurants()");
        }
    })
}

function getRestaurantsByName(){
    const name = $("#nameOfRestaurantToSearchFor").val();

    $.get({
        url: "http://43.200.83.7:80/get-restaurants-by-name",
        data : {name : name},
        dataType : "json",
        success : function (res){
            res.forEach(restaurant => {
                alert(
                    "식당 ID : " + restaurant.id + "\n"
                    + "주소 : " + restaurant.address + "\n"
                    + "별점 : " + restaurant.starPoint + "\n"
                    + "총 평 : " + restaurant.comment + "\n"
                );
            })
        },
        error(){
            alert("error : getRestaurantsByName()");
        }
    });
}

// POST
function postRestaurant(){
    const jsonNewRestaurant = {
        name : $("#nameForRegistering").val(),
        address : $("#addressForRegistering").val(),
        starPoint : $("#starPointForRegistering").val(),
        comment : $("#commentForRegistering").val()
    }

    $.post({
        url : "http://43.200.83.7:80/post-restaurant",
        data : {jsonNewRestaurant : JSON.stringify(jsonNewRestaurant)},
        success : function (){
            alert("성공적으로 새로운 식당 정보가 등록되었습니다.");
        },
        error(err){
            alert("error : postRestaurant()");
        }
    });
}

// PUT
function putRestaurantById(){
    const jsonNewRestaurant = {
        id : $("#idForModifying").val(),
        name : $("#nameForModifying").val(),
        address : $("#addressForModifying").val(),
        starPoint : $("#starPointForModifying").val(),
        comment : $("#commentForModifying").val()
    }

    $.ajax({
        url : "http://43.200.83.7:80/put-restaurant-by-id",
        method : "PUT",
        data : {jsonNewRestaurant : JSON.stringify(jsonNewRestaurant)},
        success : function (){
            alert("성공적으로 식당의 정보를 수정하였습니다.");
        },
        error(){
            alert("error : putRestaurantById()");
        }
    })
}

// DELETE
function deleteRestaurantById(){
    const id = $("#restaurantIdToDelete").val();

    $.ajax({
        url : "http://43.200.83.7:80/delete-restaurant-by-id",
        method : "DELETE",
        data : {id : id},
        success : function (){
            alert(id + "식당을 삭제 완료하였습니다.");
        },
        error(){
            alert("error : deleteRestaurantById()")
        }
    });
}