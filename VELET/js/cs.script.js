/* Velvet 2.0*/

/* SHARED VARS */
var firstrun = true,
    touch = false,
    clickEv = 'click';


var isMobile = {
  Android: function() {
    return navigator.userAgent.match(/Android/i);
  },
  BlackBerry: function() {
    return navigator.userAgent.match(/BlackBerry/i);
  },
  iOS: function() {
    return navigator.userAgent.match(/iPhone|iPad|iPod/i);
  },
  Opera: function() {
    return navigator.userAgent.match(/Opera Mini/i);
  },
  Windows: function() {
    return navigator.userAgent.match(/IEMobile/i);
  },
  any: function() {
    return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
  }
};

var switchImage = function(newImageSrc, newImage, mainImageDomEl) {

  jQuery(mainImageDomEl).attr('src', newImageSrc);
  
  if ($(window).width() > 782) {jQuery(mainImageDomEl).parent().trigger('zoom.destroy').zoom( { url: newImageSrc.replace('_master', '') } );}  
  

};



/* Fucntion get width browser */
function getWidthBrowser() {
  var myWidth;

  if( typeof( window.innerWidth ) == 'number' ) { 
    //Non-IE 
    myWidth = window.innerWidth;
    //myHeight = window.innerHeight; 
  } 
  else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) { 
    //IE 6+ in 'standards compliant mode' 
    myWidth = document.documentElement.clientWidth; 
    //myHeight = document.documentElement.clientHeight; 
  } 
  else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) { 
    //IE 4 compatible 
    myWidth = document.body.clientWidth; 
    //myHeight = document.body.clientHeight; 
  }

  return myWidth;
}

/* Function: Refresh Zoom */
function alwaysUpdateZoom(){
  if(touch == false){
    
    if($('.elevatezoom').length){

      var zoomImage = $('.elevatezoom .img-zoom');

      zoomImage.elevateZoom({
        gallery:'gallery_main', 
        galleryActiveClass: 'active', 
        zoomType: 'window',
        cursor: 'pointer',
        zoomWindowFadeIn: 300,
        zoomWindowFadeOut: 300,
        zoomWindowWidth: 280,
        zoomWindowHeight: 430,
        scrollZoom: true,
        easing : true
      });

      
      //pass the images to Fancybox
      $(".elevatezoom .img-zoom").bind("click", function(e) {  
        var ez =   $('.elevatezoom .img-zoom').data('elevateZoom');	
        $.fancybox(ez.getGalleryList(),{
          closeBtn  : false,
          helpers : {
            buttons	: {}
          }
        });
        return false;
      });
      
    }
    
  }
}

// handle Animate
function handleAnimate(){
  if(touch == false){
    $('[data-animate]').each(function(){

      var $toAnimateElement = $(this);

      var toAnimateDelay = $(this).attr('data-delay');

      var toAnimateDelayTime = 0;

      if( toAnimateDelay ) { toAnimateDelayTime = Number( toAnimateDelay ); } else { toAnimateDelayTime = 200; }

      if( !$toAnimateElement.hasClass('animated') ) {

        $toAnimateElement.addClass('not-animated');

        var elementAnimation = $toAnimateElement.attr('data-animate');

        $toAnimateElement.appear(function () {

          setTimeout(function() {
            $toAnimateElement.removeClass('not-animated').addClass( elementAnimation + ' animated');
          }, toAnimateDelayTime);

        },{accX: 0, accY: -100},'easeInCubic');

      }
    });
  }
}

function handleLogin(){
  $("#loginBox input").focus(function() {
    $(this).parents('#loginBox').addClass('focus');
  }).blur(function() {
    $(this).parents('#loginBox').removeClass('focus');
  });
}

/* Function update scroll product thumbs */
function updateScrollThumbs(){
  if($('#gallery_main').length){

    if(touch){
      $('#product-image .main-image').on('click', function() {
        var _items = [];
        var _index = 0;
        var product_images = $('#product-image .image-thumb');
        product_images.each(function(key, val) {
          _items.push({'href' : val.href, 'title' : val.title});
          if($(this).hasClass('active')){
            _index = key;
          }
        });
        $.fancybox(_items,{
          closeBtn: false,
          index: _index,
          openEffect: 'none',
          closeEffect: 'none',
          nextEffect: 'none',
          prevEffect: 'none',
          helpers: {
            buttons: {}
          }
        });
        return false;
      });
    }
    else{
      
    }

    $('#product-image').on('click', '.image-thumb', function() {

      var $this = $(this);
      var background = $('.product-image .main-image .main-image-bg');
      var parent = $this.parents('.product-image-wrapper');
      var src_original = $this.attr('data-image-zoom');
      var src_display = $this.attr('data-image');

      background.show();

      parent.find('.image-thumb').removeClass('active');
      $this.addClass('active');

      parent.find('.main-image').find('img').attr('data-zoom-image', src_original);
      parent.find('.main-image').find('img').attr('src', src_display).load(function() {
        background.hide();
      });

      return false;
    });
  }
}

/* Function update scroll product thumbs */
function updateScrollThumbsQS(){
  if($('#gallery_main_qs').length){

    $('#quick-shop-image .fancybox').on(clickEv, function() {
      var _items = [];
      var _index = 0;
      var product_images = $('#gallery_main_qs .image-thumb');
      product_images.each(function(key, val) {
        _items.push({'href' : val.href, 'title' : val.title});
        if($(this).hasClass('active')){
          _index = key;
        }
      });
      $.fancybox(_items,{
        closeBtn: true,
        index: _index,
        helpers: {
          buttons: {}
        }
      });
      return false;
    });

    $('#quick-shop-image').on(clickEv, '.image-thumb', function() {

      var $this = $(this);
      var background = $('.product-image .main-image .main-image-bg');
      var parent = $this.parents('.product-image-wrapper');
      var src_original = $this.attr('data-image-zoom');
      var src_display = $this.attr('data-image');

      background.show();

      parent.find('.image-thumb').removeClass('active');
      $this.addClass('active');

      parent.find('.main-image').find('img').attr('data-zoom-image', src_original);
      parent.find('.main-image').find('img').attr('src', src_display).load(function() {
        background.hide();
      });

      return false;
    });
  }
}

/* Handle Carousel */
function handleCarousel(){

  /* Handle product thumbs */
  if($("#gallery_main").length){
    $("#gallery_main").owlCarousel({
      navigation : true,
      pagination: false,
      items: 5,
      itemsDesktop : [1199,5],
      itemsDesktopSmall : [979,4],
      itemsTablet: [768,4],
      itemsMobile : [479,3],
      scrollPerPage: true,
      navigationText: ['<i class="fa fa-angle-left btooltip" title="Previous"></i>', '<i class="fa fa-angle-right btooltip" title="Next"></i>'],
      afterInit: function(elem){
        elem.find('.btooltip').tooltip();
      }
    });
  }


  /* Handle related products */
  if($(".prod-related").length){
    $(".prod-related").owlCarousel({
      navigation : true,
      pagination: false,
      items: 5,
      slideSpeed : 200,
      paginationSpeed : 800,
      rewindSpeed : 1000,
      itemsDesktop : [1199,5],
      itemsDesktopSmall : [979,4],
      itemsTablet: [768,3],
      itemsTabletSmall: [540,2],
      itemsMobile : [360,2],
      //scrollPerPage: true,
      navigationText: ['<i class="fa fa-chevron-left btooltip" title="Previous"></i>', '<i class="fa fa-chevron-right btooltip" title="Next"></i>'],
      beforeMove: function(elem) {
        if(touch == false){
          var items = elem.find('.not-animated');
          items.removeClass('not-animated').unbind('appear');
        }
      },
      afterInit: function(elem){
        if(touch == false){
          elem.find('.btooltip').tooltip();
        }
      }
    });
  }
}

/* Handle search box on mobile */
function callbackSearch(){
  $('.header-search i').mouseup(function(search){
    $(this).parent().find('.search-form').addClass('focus');
  });

  $('.header-search i').mouseup(function(search){
    if(!($(search.target).parent().find('.search-form').length > 0)) {
      $(this).parent().find('.search-form').removeClass('focus');
    }
  });

}
/* Handle search box on pc */
function handleBoxSearch(){
  if($('#header-search input').length){
    $('#header-search input').focus(function() {
      $(this).parent().addClass('focus');
    }).blur(function() {
      $(this).parent().removeClass('focus');
    });
  }
}

function callbackSearchPC(){
  $('.header-search').mouseover(function(search){   
    $(this).parents().find('.header-search').addClass('open');
  });
  $('.header-search').mouseout(function(search){
    $(this).parents().find('.header-search').removeClass('open');
  });
}

/* Handle Map with GMap */
function handleMap(){
  if(jQuery().gMap){
    if($('#contact_map').length){
      $('#contact_map').gMap({
        zoom: 17,
        scrollwheel: false,
        maptype: 'ROADMAP',
        markers:[
        {
        address: '249 Ung Văn Khiêm, phường 25, Ho Chi Minh City, Vietnam',
        html: '_address'
      }
                             ]
                             });
    }
  }
}

/* Handle detect platform */
function handleDetectPlatform(){
  /* DETECT PLATFORM */
  $.support.touch = 'ontouchend' in document;

  if ($.support.touch) {
    touch = true;
    $('body').addClass('touch');
    clickEv = 'touchstart';
  }
  else{
    $('body').addClass('notouch');
    if (navigator.appVersion.indexOf("Mac")!=-1){
      if (navigator.userAgent.indexOf("Safari") > -1){
        $('body').addClass('macos');
      }
      else if (navigator.userAgent.indexOf("Chrome") > -1){
        $('body').addClass('macos-chrome');
      }
      else if (navigator.userAgent.indexOf("Mozilla") > -1){
        $('body').addClass('macos-mozilla');
      }
    }
  }
}

/* Handle tooltip */
function handleToolTip(){
  if(touch == false){
    if($('.btooltip').length){
      $('.btooltip').tooltip();
    }
  }
}

/* Handle product quantity */
function handleQuantity(){
  if($('.quantity-wrapper').length){
    $('.quantity-wrapper').on(clickEv, '.qty-up', function() {
      var $this = $(this);

      var qty = $this.data('src');
      $(qty).val(parseInt($(qty).val()) + 1);
    });
    $('.quantity-wrapper').on(clickEv, '.qty-down', function() {
      var $this = $(this);

      var qty = $this.data('src');

      if(parseInt($(qty).val()) > 1)
        $(qty).val(parseInt($(qty).val()) - 1);
    });
  }
}


/* Handle dropdown box */
function handleDropdown(){

  $('.dropdown').on('click', function() {
    if(touch == false && getWidthBrowser() > 767 ){
      var href = $(this).find('.dropdown-link').attr('href');
      window.location = href;
    }
  });

  $('.cart-link').on('click', function() {
    if(touch == false && getWidthBrowser() > 767 ){
      var href = $(this).find('.dropdown-link').attr('href');
      window.location = href;
    }
  });
}

/* Handle Footer */
function handleFooter(){
  $('.plus-footer').click(function(search){
    if(!$(this).parents().find('#footer-content').hasClass('close-fc')){
      $('#footer-content').addClass('close-fc');
    }
    else{
      $('#footer-content').removeClass('close-fc');
    }
    
    if(!$(this).find('.fi-hide').hasClass('fa-bars')){
      $('.fi-hide').addClass('fa-bars');
      $('.fi-hide').removeClass('fa-close');
    }
    else{
      $('.fi-hide').removeClass('fa-bars');
      $('.fi-hide').addClass('fa-close');
    }
    
  });
}

/* Handle when window resize */
$(window).resize(function() {

  /* reset menu with condition */
  if(touch == true || getWidthBrowser() < 1024){
    if($('#top').hasClass('on')){
      $('#top').prev().remove();
      $('#top').removeClass('on').removeClass('animated');
    }
  }
});

/* handle when window loaded */
$(window).load(function() {
  if(touch == false){
    skrollr.init();
  }

});

jQuery(document).ready(function($) {
  $('#product .thumbs a').on('click', function(e) {
    var $this = $(this);
    var parent = $this.parents('.product-image');
    e.preventDefault();
    parent.find('.cloud-zoom-gallery').removeClass('active');
    $this.addClass('active');
    switchImage($(this).attr('href'), null, $('.featured img')[0]);
  } );

  /* DETECT PLATFORM */
  handleDetectPlatform();

  /* search */
  callbackSearchPC();

  /* Handle Animate */
  handleAnimate();

  /* Handle Carousel */
  handleCarousel();
  
  /* Handle search box on pc */
  handleBoxSearch();

  /* Handle focus */
  handleLogin();

  /* Handle dropdown box */
  handleDropdown();

  /* Handle tooltip */
  handleToolTip();

  /* Handle Map with GMap */
  handleMap();

  /* Handle zoom for product image */
  alwaysUpdateZoom();

  /* Handle quantity */
  handleQuantity();
  
  /* Handle Footer */
  handleFooter();

  /* Handle product thumbs */
  if(touch){
    updateScrollThumbs();
  }
  else{
    
  }

  $('.dropdown-menu').on(clickEv, function (e) {
    e.stopPropagation();
  });
  $('.dropdown-menu').on('click', function (e) {
    e.stopPropagation();
  });
  $('.btn-navbar').on('click', function() {
    return true;
  });
  
  $("input#email-input").focus(function() {
    $(this).parents().find('.widget-wrapper form').addClass('focus');
  }).blur(function() {
    $(this).parents().find('.widget-wrapper form').removeClass('focus');
  });
  
});