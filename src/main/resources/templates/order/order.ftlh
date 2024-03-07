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

<div class="${modal}" id="order-alert-modal">
    <div class="modal__box">

        <button onclick="closeOrderAlertForm()" class="modal__close-btn" id="close-my-modal-btn">
            <i class="fa-solid fa-xmark"></i>
        </button>

        <form id="order-checkout" onsubmit="createOrder(event)">
            <#if companies?has_content>
                <p>You have ${size!''} different companies in your basket, please choose only one for checkout</p>
                <div class="container-buttons">
                    <#list companies as company>
                        <input type="submit" value="${company}">
                    </#list>
                </div>
            </#if>
        </form>
    </div>
</div>

