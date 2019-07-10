$(document).ready(function () {

    $("#login").click(function () {
        $(this).addClass("active");
        $("#singup").removeClass("active");
        $(".login-form").show();
        $(".singup-form").hide();
    });
    $("#singup").click(function () {
        $(this).addClass("active");
        $("#login").removeClass("active");
        $(".login-form").hide();
        $(".singup-form").show();
    });

    $('#form-created-staff').submit(function (e) {
        $(".singup-form").removeClass("singup-form");
        $("#singup  ").addClass("active");
        $("#login").removeClass("active");
        $(".login-form").hide();
        $(".singup-form").show();
    });

// $("#btn-login").click(function () {
//     var email = $("#input-icon-email").val();
//     var password = $("#input-icon-password").val();
//
//     $.ajax({
//         url:"/api/checkLogin",
//         type: "POST",
//         data:{
//             email:email,
//             password:password
//         },
//         dataType:'text',
//         success:function (res) {
//             if (res == "index") {
//                 path =window.location.origin;
//                 window.location =  path;
//             }
//             if (res == "login") {
//                 path = window.location.href;
//                 window.location = path;
//             }
//             // if (res == "/login/") {
//             //     // document.write(res);
//             //     window.location.href;
//             // } else {
//             //     window.location.origin;
//             //     // pathPresent = window.location.href;
//             //     // path = pathPresent.replace("login/", "");
//             //     // window.location.href = path;
//             // }
//
//
//         },
//         error:function (res) {
//
//         },
//     })
// }
// );


    $(".btn-cart").click(function () {
            var productDetailId = $(this).attr("data-productDI");
            var colorName = $(this).closest("tr").find(".color").text();
            var colorId = $(this).closest("tr").find(".color").attr("data-color");
            var sizeName = $(this).closest("tr").find(".size").text();
            var sizeId = $(this).closest("tr").find(".size").attr("data-size");
            var productName = $("#product-name").text();
            var productPrice = $("#product-price").attr("data-value");
            var productId = $("#product-name").attr("data-productId");

            $.ajax({
                url: "/api/addCart",
                type: "get",
                data: {
                    productId: productId,
                    productColorId: colorId,
                    productSizeId: sizeId,
                    productName: productName,
                    price: productPrice,
                    colorName: colorName,
                    sizeName: sizeName,
                    productDetailId: productDetailId,
                },
                dataType: '',
                success: function (res) {
                    $.ajax({
                        url: '/api/getProductDeal',
                        type: 'get',
                        success: function (e) {
                            $("#id-cart").find("div").addClass("dealCart");
                            $("#id-cart").find("div").html("<span>" + e + "</span>");
                        }
                    })
                },
                error: function (res) {

                },
            })
        }
    );


    $('.price').text(function () {
        var str = $(this).html() + '';
        x = str.split('.');
        x1 = x[0];
        x2 = x.length > 1 ? '.' + x[1] : '';
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + ',' + '$2');
        }
        $(this).html(x1 + x2);
    });

    function formatNumber(nStr, decSeperate, groupSeperate) {
        nStr += '';
        x = nStr.split(decSeperate);
        x1 = x[0];
        x2 = x.length > 1 ? '.' + x[1] : '';
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + groupSeperate + '$2');
        }
        return x1 + x2;
    }


    assignTotalPriceCart();

    function assignTotalPriceCart(isChange) {
        var totalCart = 0;
        $(".price-cart").each(function () {
            price = $(this).text();
            returnDefaultTypePrice = price.replace(",", "");
            var x = /,/g;
            while (x.test(returnDefaultTypePrice)) {
                returnDefaultTypePrice = returnDefaultTypePrice.replace(",", "");
            }

            deal = $(this).closest("tr").find(".deal-cart").val();
            if (deal > 1 && !isChange) {
                totalDefault = deal * returnDefaultTypePrice;
                total = formatNumber(totalDefault, '.', ',');
                $(this).html(total);

                totalCart = totalCart + parseInt(totalDefault);
                formmatTotalPrice = formatNumber(totalCart, ".", ",");
                $('#total-price').html(formmatTotalPrice);

            } else {
                totalCart = totalCart + parseInt(returnDefaultTypePrice);
                formmatTotalPrice = formatNumber(totalCart, ".", ",");
                $('#total-price').html(formmatTotalPrice);
            }
        })
    }


    $(".deal-cart").change(function () {
        var deal = $(this).val();
        var price = $(this).closest("tr").find(".price-cart").attr("data-price");
        var total = deal * parseInt(price);
        var format = formatNumber(total, '.', ',');
        $(this).closest("tr").find(".price-cart").html(format);
        assignTotalPriceCart(true);

        var colorId = $(this).closest("tr").find(".color").attr("data-color");
        var sizeId = $(this).closest("tr").find(".size").attr("data-size");
        var productId = $(this).closest("tr").find(".product-name-cart").attr("data-productId");

        $.ajax({
            url: "/api/updateCart",
            type: "GET",
            data: {
                productId: productId,
                colorId: colorId,
                sizeId: sizeId,
                deal: deal,
            },
            dataType: '',
            success: function (res) {

            },
            error: function (res) {
                console.log();
            },
        })

    });
    //


    $('.deleta-product-cart').click(function () {
        var self = $(this);
        var colorId = $(this).closest("tr").find(".color").attr("data-color");
        var sizeId = $(this).closest("tr").find(".size").attr("data-size");
        var productId = $(this).closest("tr").find(".product-name-cart").attr("data-productId");

        $.ajax({
            url: "/api/deleteProductInCart",
            type: "GET",
            data: {
                productId: productId,
                colorId: colorId,
                sizeId: sizeId,
            },
            dataType: '',
            success: function (res) {
                self.closest("tr").remove();
                assignTotalPriceCart(true);

            },
            error: function (res) {
                console.log();
            },
        })


    });

    $('body').on('click', '.page-item', function () {
        $('.page-item').removeClass('active');
        $(this).addClass('active');
        dealPage = $(this).text();
        productStart = (dealPage - 1) * 2;
        $.ajax({
            url: "/api/getListProductByPage",
            type: "GET",
            data: {
                productStart: productStart,

            },
            dataType: 'html',
            success: function (res) {
                $('#body-table').html(res);
            },
            error: function (res) {
                console.log();
            },
        })
    })

    $('#check-all').change(function () {
        if (this.checked) {
            $('#body-table-product input').each(function () {
                $(this).attr('checked', true);
            })
        } else {
            $('#body-table-product input').each(function () {
                $(this).attr('checked', false);
            })
        }
    })


    $('#deleteProduct').click(function () {
        $('#body-table-product input:checked').each(function () {
            var productId = $(this).val();
            var This = $(this);

            $.ajax({
                url: "/api/deleteProduct",
                type: "GET",
                data: {
                    productId: productId,

                },
                dataType: '',
                success: function (res) {
                    This.closest('tr').remove();
                },
                error: function (res) {
                    console.log(res);
                },
            })

        })
    })

    var files = [];

    imageName = '';

    $('#productImage').change(function (event) {
        files = event.target.files;
        forms = new FormData();
        imageName = files[0].name;
        forms.append("file", files[0]);
        $.ajax({
            url: "/api/uploadFiles",
            type: "Post",
            data: forms,
            contentType: false,
            enctype: "multipart/form-data",
            processData: false,
            success: function (res) {

            },
            error: function (res) {
                console.log(res);
            },
        })
    })

    $('body').on('click', '.btn-details', function () {
        $(this).remove();
        divClone = $('#productDetails').clone().removeAttr('id');
        $('#clone-at').append(divClone);
    })


    $('#btn-add-product').click(function (event) {
        event.preventDefault();
        formtData = $('#form-product').serializeArray();
        json = {};
        arrayDetails = [];
        $.each(formtData, function (i, field) {
            json [field.name] = field.value;

        });

        $('#clone-at > .productDetails').each(function () {
            objectDetail = {};
            productColor = $(this).find('select[name="productColor"]').val();
            productSize = $(this).find('select[name="productSize"]').val();
            dealProduct = $(this).find('input[name="dealProduct"]').val();

            objectDetail ['productColor'] = productColor;
            objectDetail ['productSize'] = productSize;
            objectDetail ['dealProduct'] = dealProduct;

            arrayDetails.push(objectDetail);

        })

        json ['productDetails'] = arrayDetails;
        json ['imageName'] = imageName;

        $.ajax({
            url: "/api/apiAddProduct",
            type: "Post",
            data: {
                dataJson: JSON.stringify(json)
            },
            success: function (res) {

            },
            error: function (res) {
                console.log(res);
            },
        })
    })

    productId='';
    $('body').on('click', '.updateProduct', function () {
        productId = $(this).attr('data-id');
        $('#btn-add-product').addClass('hidden');
        $('#btn-update').removeClass('hidden');
        $('#btn-cancel').removeClass('hidden');

        $.ajax({
            url: "/api/getProductById",
            type: "Post",
            data: {
                productId: productId,
            },
            success: function (res) {
                console.log(res);
                $('#productName').val(res.productName);
                $('#productPrice').val(res.price);
                $('#catalog').val(res.catalogEntity.catalogId);
                $('#productDecription').val(res.productDescription);
                if (res.reserve == 'nam') {
                    $('#reserve-m').prop('checked', true);
                } else {
                    $('#reserve-w').prop('checked', true);
                }

                $('#clone-at').html('');
                for (i = 0; i < res.productDetailEntities.length; i++) {
                    detailClone = $('#productDetails').clone().removeAttr('id');
                    detailClone.find('#productColor').val(res.productDetailEntities[i].productColorEntity.productColorId);
                    detailClone.find('#productSize').val(res.productDetailEntities[i].productSizeEntity.productSizeId);
                    detailClone.find('#dealProduct').val(res.productDetailEntities[i].deal);

                    if (i < res.productDetailEntities.length - 1) {
                        detailClone.find('.btn-details').remove();
                    }

                    $('#clone-at').append(detailClone);

                }


            },
            error: function (res) {
                console.log(res);
            },
        });

    })

    $('#btn-update').click(function (event) {
        event.preventDefault();
        formtData = $('#form-product').serializeArray();
        json = {};
        arrayDetails = [];
        $.each(formtData, function (i, field) {
            json [field.name] = field.value;

        });

        $('#clone-at > .productDetails').each(function () {
            objectDetail = {};
            productColor = $(this).find('select[name="productColor"]').val();
            productSize = $(this).find('select[name="productSize"]').val();
            dealProduct = $(this).find('input[name="dealProduct"]').val();

            objectDetail ['productColor'] = productColor;
            objectDetail ['productSize'] = productSize;
            objectDetail ['dealProduct'] = dealProduct;

            arrayDetails.push(objectDetail);

        })
        json ['productId'] = productId;
        json ['productDetails'] = arrayDetails;
        json ['imageName'] = imageName;

        $.ajax({
            url: "/api/apiupdateProduct",
            type: "Post",
            data: {
                dataJson: JSON.stringify(json)
            },
            success: function (res) {

            },
            error: function (res) {
                console.log(res);
            },
        })
    })

})



