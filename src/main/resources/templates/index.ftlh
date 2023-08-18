<#import "parts/common.ftlh" as c>

<@c.page>
<img src="/img/123.jpg" class="img-fluid rounded-start">


<div id="carouselWithIndicators" class="carousel slide" data-bs-ride="carousel">
    <ol class="carousel-indicators">
        <li data-bs-target="#carouselWithIndicators" data-bs-slide-to="0" class="active"></li>
        <li data-bs-target="#carouselWithIndicators" data-bs-slide-to="1"></li>
        <li data-bs-target="#carouselWithIndicators" data-bs-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="/img/1.jpg" class="d-block w-100" alt="Slide 1">
        </div>
        <div class="carousel-item">
            <img src="/img/1.jpg" class="d-block w-100" alt="Slide 2">
        </div>
        <div class="carousel-item">
            <img src="/img/1.jpg" class="d-block w-100" alt="Slide 3">
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselWithIndicators" role="button" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselWithIndicators" role="button" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </a>
</div>
</@c.page>