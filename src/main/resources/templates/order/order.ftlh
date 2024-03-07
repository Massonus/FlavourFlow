<div class="modal" id="order-modal">
    <div class="modal__box">

        <button onclick="closeOrderForm()" class="modal__close-btn" id="close-my-modal-btn">
            <i class="fa-solid fa-xmark"></i>
        </button>

        <form id="order-checkout" onsubmit="createOrder(event)">
            <input id="orderName" name="name" type="text" placeholder="Your name">
            <input id="orderPhone" name="phone" type="text" placeholder="Mobile phone">
            <input type="date" name="date" id="date">

            <input type="hidden" id="_csrf" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Отправить">
        </form>

    </div>
</div>

