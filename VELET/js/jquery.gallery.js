(function() {
  var lastTime = 0;
  var vendors = ['ms', 'moz', 'webkit', 'o'];
  for(var x = 0; x < vendors.length && !window.requestAnimationFrame; ++x) {
    window.requestAnimationFrame = window[vendors[x]+'RequestAnimationFrame'];
    window.cancelAnimationFrame = window[vendors[x]+'CancelAnimationFrame'] || window[vendors[x]+'CancelRequestAnimationFrame'];
  }

  if (!window.requestAnimationFrame) {
    window.requestAnimationFrame = function(callback, element) {
      var currTime = new Date().getTime();
      var timeToCall = Math.max(0, 16 - (currTime - lastTime));
      var id = window.setTimeout(function() { callback(currTime + timeToCall); },
       timeToCall);
      lastTime = currTime + timeToCall;
      return id;
    };
  }

  if (!window.cancelAnimationFrame) {
    window.cancelAnimationFrame = function(id) {
      clearTimeout(id);
    };
  }
}());
function debounce(a,b,c){var d;return function(){var e=this,f=arguments;clearTimeout(d),d=setTimeout(function(){d=null,c||a.apply(e,f)},b),c&&!d&&a.apply(e,f)}}
var w = window.innerWidth,
    desktopBreakPoint = 769;

function updateCoverImages() {
  w = window.innerWidth;
  if ( w < desktopBreakPoint ) {
    return;
  }

  $('.slide__image').each(function() {

    var el = $(this),
        iW = el.width();
    $('.gallery__slide').css('width', iW);
  });

};

function galleryLoadImages1()
{
  // -------------------------------------------------------------------------------------
  // Gallery Tab01
  // -------------------------------------------------------------------------------------
  var gallery = $('.gallery-1');
  if (gallery.length) {

    // Steal the scroll event for the gallery only
    var movement = 0;
    var rightarrow = 0;
    $('.gallery-1 .gallery__slide').each(function(i) {
        rightarrow += $(this).width()-4;
     });
    rightarrow -=$('#makeMeScrollable').innerWidth();
    
    gallery.on('mousewheel', function(event) {
      if ( w >= desktopBreakPoint ) {
        event.preventDefault();
        if (event.deltaX > 0.5 || event.deltaX < -0.5) {
          this.scrollLeft += (event.deltaX * event.deltaFactor);
          arrowVisibility(this.scrollLeft,rightarrow);
        } else {
          this.scrollLeft += (event.deltaY * event.deltaFactor);
          arrowVisibility(this.scrollLeft,rightarrow);
        }
      };
    });


    // Keyboard/arrow scrolling
    var currentScroll = 0,
        isScrolling = false;

    // Setup keyboard arrow scroll capability
    $(document).keydown(function(event){
      switch (event.keyCode) {
        case 37: // left
          event.preventDefault();
          if (!isScrolling) {
            isScrolling = true;
            scrollGallery('left');
          }
          break;
        case 39: // right
          event.preventDefault();
          if (!isScrolling) {
            isScrolling = true;
            scrollGallery('right');
          }
          break;
      }
    });
    
      var leftArrow = '<div class="gallery-arrow gallery-arrow-1 gallery-left"></div>',
          rightArrow = '<div class="gallery-arrow gallery-arrow-1 gallery-right"></div>',
          autoInterval = null;

      var numSlides = $('.gallery-1 .gallery__slide').length;
      var totalGalleryWidth = 0;
      $('.gallery-1 .gallery__slide').each(function(i) {
        totalGalleryWidth += $(this).width();
        if ((i + 1) == numSlides) {
          if(totalGalleryWidth > $('.gallery-1 .gallery__inner').width()) {
            gallery.append(leftArrow).append(rightArrow);
          }
        }
      });

      var autoIncrement = 10,
          intervalSpeed = 1;

      var scrollLeftArrow = $('.gallery-arrow-1.gallery-left'),
          scrollRightArrow = $('.gallery-arrow-1.gallery-right').show();

      $('.gallery-1 .gallery-arrow-1').mouseenter(function() {
        var el = $(this);
        if (el.hasClass('gallery-left')) {
          autoScrollGallery('left');
        } else {
          autoScrollGallery('right');
        }
      }).mouseleave(function() {
        clearInterval(autoInterval);
      });
    

    function autoScrollGallery(direction) {
      if (isScrolling) return;
      var scrollTracker = 0;

      autoInterval = setInterval(function() {
        switch(direction) {
          case 'right':
            var newLeft = gallery.scrollLeft() + autoIncrement;
            gallery.scrollLeft(newLeft);
            scrollTracker = gallery.scrollLeft();
            arrowVisibility(newLeft, scrollTracker);
            break;
          case 'left':
            var newLeft = gallery.scrollLeft()-autoIncrement > 0 ? (gallery.scrollLeft()-autoIncrement) : 0;
            gallery.scrollLeft(newLeft);
            arrowVisibility(newLeft);
            break;
        }
      }, intervalSpeed);
    }

    function scrollGallery(dir, speed, amount) {
      dir = dir ? dir : 'right';
      speed = speed ? speed : 200;
      amount = amount ? amount : 650;

      switch(dir) {
        case 'right':
          gallery.animate({
            scrollLeft: gallery.scrollLeft()+amount
          }, speed, function() {
            isScrolling = false;
          });
          break;
        case 'left':
          gallery.animate({
            scrollLeft: gallery.scrollLeft()-amount > 0 ? (gallery.scrollLeft()-amount) : 0
          }, speed, function() {
            isScrolling = false;
          });
          break;
      }
    }

    function arrowVisibility(newLeft, oldLeft) {
      if (newLeft == 0) {
        scrollLeftArrow.fadeOut(150);
      }

      else if ( newLeft > oldLeft ) {
        scrollRightArrow.fadeOut(150);
      }

      else {
       scrollRightArrow.fadeIn(200);
       scrollLeftArrow.fadeIn(200);
      }

    }

  }
}
function galleryLoadImages2()
{
  	// -------------------------------------------------------------------------------------
  // Gallery Tab02
  // -------------------------------------------------------------------------------------
  var gallery = $('.gallery-2');
  if (gallery.length) {

    // Steal the scroll event for the gallery only
    var movement = 0;
    var rightarrow = 0;
    $('.gallery-2 .gallery__slide').each(function(i) {
        rightarrow += $(this).width()-4;
     });
    rightarrow -=$('#makeMeScrollable').innerWidth();
    
    gallery.on('mousewheel', function(event) {
      if ( w >= desktopBreakPoint ) {
        event.preventDefault();
        if (event.deltaX > 0.5 || event.deltaX < -0.5) {
          this.scrollLeft += (event.deltaX * event.deltaFactor);
          arrowVisibility(this.scrollLeft,rightarrow);
        } else {
          this.scrollLeft += (event.deltaY * event.deltaFactor);
          arrowVisibility(this.scrollLeft,rightarrow);
        }
      };
    });


    // Keyboard/arrow scrolling
    var currentScroll = 0,
        isScrolling = false;

    // Setup keyboard arrow scroll capability
    $(document).keydown(function(event){
      switch (event.keyCode) {
        case 37: // left
          event.preventDefault();
          if (!isScrolling) {
            isScrolling = true;
            scrollGallery('left');
          }
          break;
        case 39: // right
          event.preventDefault();
          if (!isScrolling) {
            isScrolling = true;
            scrollGallery('right');
          }
          break;
      }
    });
    
      var leftArrow = '<div class="gallery-arrow gallery-arrow-2 gallery-left"></div>',
          rightArrow = '<div class="gallery-arrow gallery-arrow-2 gallery-right"></div>',
          autoInterval = null;

      var numSlides = $('.gallery-2 .gallery__slide').length;
      var totalGalleryWidth = 0;
      $('.gallery-2 .gallery__slide').each(function(i) {
        totalGalleryWidth += $(this).width();
        if ((i + 1) == numSlides) {
          if(totalGalleryWidth > $('.gallery-2 .gallery__inner').width()) {
            gallery.append(leftArrow).append(rightArrow);
          }
        }
      });

      var autoIncrement = 10,
          intervalSpeed = 1;

      var scrollLeftArrow = $('.gallery-arrow-2.gallery-left'),
          scrollRightArrow = $('.gallery-arrow-2.gallery-right').show();

      $('.gallery-2 .gallery-arrow-2').mouseenter(function() {
        var el = $(this);
        if (el.hasClass('gallery-left')) {
          autoScrollGallery('left');
        } else {
          autoScrollGallery('right');
        }
      }).mouseleave(function() {
        clearInterval(autoInterval);
      });
    

    function autoScrollGallery(direction) {
      if (isScrolling) return;
      var scrollTracker = 0;

      autoInterval = setInterval(function() {
        switch(direction) {
          case 'right':
            var newLeft = gallery.scrollLeft() + autoIncrement;
            gallery.scrollLeft(newLeft);
            scrollTracker = gallery.scrollLeft();
            arrowVisibility(newLeft, scrollTracker);
            break;
          case 'left':
            var newLeft = gallery.scrollLeft()-autoIncrement > 0 ? (gallery.scrollLeft()-autoIncrement) : 0;
            gallery.scrollLeft(newLeft);
            arrowVisibility(newLeft);
            break;
        }
      }, intervalSpeed);
    }

    function scrollGallery(dir, speed, amount) {
      dir = dir ? dir : 'right';
      speed = speed ? speed : 200;
      amount = amount ? amount : 650;

      switch(dir) {
        case 'right':
          gallery.animate({
            scrollLeft: gallery.scrollLeft()+amount
          }, speed, function() {
            isScrolling = false;
          });
          break;
        case 'left':
          gallery.animate({
            scrollLeft: gallery.scrollLeft()-amount > 0 ? (gallery.scrollLeft()-amount) : 0
          }, speed, function() {
            isScrolling = false;
          });
          break;
      }
    }

    function arrowVisibility(newLeft, oldLeft) {
      if (newLeft == 0) {
        scrollLeftArrow.fadeOut(150);
      }

      else if ( newLeft > oldLeft ) {
        scrollRightArrow.fadeOut(150);
      }

      else {
       scrollRightArrow.fadeIn(200);
       scrollLeftArrow.fadeIn(200);
      }

    }

  }
}
function galleryLoadImages3()
{
  	// -------------------------------------------------------------------------------------
  // Gallery Tab03
  // -------------------------------------------------------------------------------------
  var gallery = $('.gallery-3');
  if (gallery.length) {

    // Steal the scroll event for the gallery only
    var movement = 0;
    var rightarrow = 0;
    $('.gallery-3 .gallery__slide').each(function(i) {
        rightarrow += $(this).width()-4;
     });
    rightarrow -=$('#makeMeScrollable').innerWidth();
    
    gallery.on('mousewheel', function(event) {
      if ( w >= desktopBreakPoint ) {
        event.preventDefault();
        if (event.deltaX > 0.5 || event.deltaX < -0.5) {
          this.scrollLeft += (event.deltaX * event.deltaFactor);
          arrowVisibility(this.scrollLeft,rightarrow);
        } else {
          this.scrollLeft += (event.deltaY * event.deltaFactor);
          arrowVisibility(this.scrollLeft,rightarrow);
        }
      };
    });


    // Keyboard/arrow scrolling
    var currentScroll = 0,
        isScrolling = false;

    // Setup keyboard arrow scroll capability
    $(document).keydown(function(event){
      switch (event.keyCode) {
        case 37: // left
          event.preventDefault();
          if (!isScrolling) {
            isScrolling = true;
            scrollGallery('left');
          }
          break;
        case 39: // right
          event.preventDefault();
          if (!isScrolling) {
            isScrolling = true;
            scrollGallery('right');
          }
          break;
      }
    });
    
      var leftArrow = '<div class="gallery-arrow gallery-arrow-3 gallery-left"></div>',
          rightArrow = '<div class="gallery-arrow gallery-arrow-3 gallery-right"></div>',
          autoInterval = null;

      var numSlides = $('.gallery-3 .gallery__slide').length;
      var totalGalleryWidth = 0;
      $('.gallery-3 .gallery__slide').each(function(i) {
        totalGalleryWidth += $(this).width();
        if ((i + 1) == numSlides) {
          if(totalGalleryWidth > $('.gallery-3 .gallery__inner').width()) {
            gallery.append(leftArrow).append(rightArrow);
          }
        }
      });

      var autoIncrement = 10,
          intervalSpeed = 1;

      var scrollLeftArrow = $('.gallery-arrow-3.gallery-left'),
          scrollRightArrow = $('.gallery-arrow-3.gallery-right').show();

      $('.gallery-3 .gallery-arrow-3').mouseenter(function() {
        var el = $(this);
        if (el.hasClass('gallery-left')) {
          autoScrollGallery('left');
        } else {
          autoScrollGallery('right');
        }
      }).mouseleave(function() {
        clearInterval(autoInterval);
      });
    

    function autoScrollGallery(direction) {
      if (isScrolling) return;
      var scrollTracker = 0;

      autoInterval = setInterval(function() {
        switch(direction) {
          case 'right':
            var newLeft = gallery.scrollLeft() + autoIncrement;
            gallery.scrollLeft(newLeft);
            scrollTracker = gallery.scrollLeft();
            arrowVisibility(newLeft, scrollTracker);
            break;
          case 'left':
            var newLeft = gallery.scrollLeft()-autoIncrement > 0 ? (gallery.scrollLeft()-autoIncrement) : 0;
            gallery.scrollLeft(newLeft);
            arrowVisibility(newLeft);
            break;
        }
      }, intervalSpeed);
    }

    function scrollGallery(dir, speed, amount) {
      dir = dir ? dir : 'right';
      speed = speed ? speed : 200;
      amount = amount ? amount : 650;

      switch(dir) {
        case 'right':
          gallery.animate({
            scrollLeft: gallery.scrollLeft()+amount
          }, speed, function() {
            isScrolling = false;
          });
          break;
        case 'left':
          gallery.animate({
            scrollLeft: gallery.scrollLeft()-amount > 0 ? (gallery.scrollLeft()-amount) : 0
          }, speed, function() {
            isScrolling = false;
          });
          break;
      }
    }

    function arrowVisibility(newLeft, oldLeft) {
      if (newLeft == 0) {
        scrollLeftArrow.fadeOut(150);
      }

      else if ( newLeft > oldLeft ) {
        scrollRightArrow.fadeOut(150);
      }

      else {
       scrollRightArrow.fadeIn(200);
       scrollLeftArrow.fadeIn(200);
      }

    }

  }
}
function galleryLoadImages4()
{
  	// -------------------------------------------------------------------------------------
  // Gallery Tab04
  // -------------------------------------------------------------------------------------
  var gallery = $('.gallery-4');
  if (gallery.length) {

    // Steal the scroll event for the gallery only
    var movement = 0;
    var rightarrow = 0;
    $('.gallery-1 .gallery__slide').each(function(i) {
        rightarrow += $(this).width()-4;
     });
    rightarrow -=$('#makeMeScrollable').innerWidth();
    
    gallery.on('mousewheel', function(event) {
      if ( w >= desktopBreakPoint ) {
        event.preventDefault();
        if (event.deltaX > 0.5 || event.deltaX < -0.5) {
          this.scrollLeft += (event.deltaX * event.deltaFactor);
          arrowVisibility(this.scrollLeft,rightarrow);
        } else {
          this.scrollLeft += (event.deltaY * event.deltaFactor);
          arrowVisibility(this.scrollLeft,rightarrow);
        }
      };
    });


    // Keyboard/arrow scrolling
    var currentScroll = 0,
        isScrolling = false;

    // Setup keyboard arrow scroll capability
    $(document).keydown(function(event){
      switch (event.keyCode) {
        case 37: // left
          event.preventDefault();
          if (!isScrolling) {
            isScrolling = true;
            scrollGallery('left');
          }
          break;
        case 39: // right
          event.preventDefault();
          if (!isScrolling) {
            isScrolling = true;
            scrollGallery('right');
          }
          break;
      }
    });
    
      var leftArrow = '<div class="gallery-arrow gallery-arrow-4 gallery-left"></div>',
          rightArrow = '<div class="gallery-arrow gallery-arrow-4 gallery-right"></div>',
          autoInterval = null;

      var numSlides = $('.gallery-4 .gallery__slide').length;
      var totalGalleryWidth = 0;
      $('.gallery-4 .gallery__slide').each(function(i) {
        totalGalleryWidth += $(this).width();
        if ((i + 1) == numSlides) {
          if(totalGalleryWidth > $('.gallery-4 .gallery__inner').width()) {
            gallery.append(leftArrow).append(rightArrow);
          }
        }
      });

      var autoIncrement = 10,
          intervalSpeed = 1;

      var scrollLeftArrow = $('.gallery-arrow-4.gallery-left'),
          scrollRightArrow = $('.gallery-arrow-4.gallery-right').show();

      $('.gallery-4 .gallery-arrow-4').mouseenter(function() {
        var el = $(this);
        if (el.hasClass('gallery-left')) {
          autoScrollGallery('left');
        } else {
          autoScrollGallery('right');
        }
      }).mouseleave(function() {
        clearInterval(autoInterval);
      });
    

    function autoScrollGallery(direction) {
      if (isScrolling) return;
      var scrollTracker = 0;

      autoInterval = setInterval(function() {
        switch(direction) {
          case 'right':
            var newLeft = gallery.scrollLeft() + autoIncrement;
            gallery.scrollLeft(newLeft);
            scrollTracker = gallery.scrollLeft();
            arrowVisibility(newLeft, scrollTracker);
            break;
          case 'left':
            var newLeft = gallery.scrollLeft()-autoIncrement > 0 ? (gallery.scrollLeft()-autoIncrement) : 0;
            gallery.scrollLeft(newLeft);
            arrowVisibility(newLeft);
            break;
        }
      }, intervalSpeed);
    }

    function scrollGallery(dir, speed, amount) {
      dir = dir ? dir : 'right';
      speed = speed ? speed : 200;
      amount = amount ? amount : 650;

      switch(dir) {
        case 'right':
          gallery.animate({
            scrollLeft: gallery.scrollLeft()+amount
          }, speed, function() {
            isScrolling = false;
          });
          break;
        case 'left':
          gallery.animate({
            scrollLeft: gallery.scrollLeft()-amount > 0 ? (gallery.scrollLeft()-amount) : 0
          }, speed, function() {
            isScrolling = false;
          });
          break;
      }
    }

    function arrowVisibility(newLeft, oldLeft) {
      if (newLeft == 0) {
        scrollLeftArrow.fadeOut(150);
      }

      else if ( newLeft > oldLeft ) {
        scrollRightArrow.fadeOut(150);
      }

      else {
       scrollRightArrow.fadeIn(200);
       scrollLeftArrow.fadeIn(200);
      }

    }

  }
}
$(window).load(function() {
  /* Load Slider */
  updateCoverImages();
  galleryLoadImages1();
  galleryLoadImages2();
  galleryLoadImages3();
  galleryLoadImages4();
  
  /* touch event*/
  if (Modernizr.touch) {
      $('body').addClass('is-touch');
  }
  
  /* owlCarousel in Mobile */
  $(".owlcarousel-tab01").owlCarousel({
      navigation : true,
      pagination: false,
      items: 5,
      itemsDesktop : [1199,0],
      itemsDesktopSmall : [979,0],
      itemsTablet: [768,2],
      itemsMobile : [479,1],
      scrollPerPage: true,
      navigationText: ['<div class="gallery-arrow gallery-left"></div>', '<div class="gallery-arrow gallery-right"></div>'],
      afterInit: function(elem){
        elem.find('.btooltip').tooltip();
      }
    });
  $(".owlcarousel-tab02").owlCarousel({
      navigation : true,
      pagination: false,
      items: 5,
      itemsDesktop : [1199,0],
      itemsDesktopSmall : [979,0],
      itemsTablet: [768,2],
      itemsMobile : [479,1],
      scrollPerPage: true,
      navigationText: ['<div class="gallery-arrow gallery-left"></div>', '<div class="gallery-arrow gallery-right"></div>'],
      afterInit: function(elem){
        elem.find('.btooltip').tooltip();
      }
    });
  $(".owlcarousel-tab03").owlCarousel({
      navigation : true,
      pagination: false,
      items: 5,
      itemsDesktop : [1199,0],
      itemsDesktopSmall : [979,0],
      itemsTablet: [768,2],
      itemsMobile : [479,1],
      scrollPerPage: true,
      navigationText: ['<div class="gallery-arrow gallery-left"></div>', '<div class="gallery-arrow gallery-right"></div>'],
      afterInit: function(elem){
        elem.find('.btooltip').tooltip();
      }
    });
  $(".owlcarousel-tab04").owlCarousel({
      navigation : true,
      pagination: false,
      items: 5,
      itemsDesktop : [1199,0],
      itemsDesktopSmall : [979,0],
      itemsTablet: [768,2],
      itemsMobile : [479,1],
      scrollPerPage: true,
      navigationText: ['<div class="gallery-arrow gallery-left"></div>', '<div class="gallery-arrow gallery-right"></div>'],
      afterInit: function(elem){
        elem.find('.btooltip').tooltip();
      }
    });
  
  /*Active UI tab*/
  $( "#homepage-tabs" ).tabs({show: 'fadeIn', hide: 'fadeOut',duration: 300}).addClass( "ui-tabs-vertical ui-helper-clearfix" );
  
});
