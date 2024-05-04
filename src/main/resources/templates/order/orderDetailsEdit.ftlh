<#import "../parts/common.ftlh" as c>

<@c.page>

    <section>
        <div class="container py-5">
            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">Edit order</h2>
                <div style="max-width: 600px" class="w-100">
                    <form onsubmit="editOrder(event, '${_csrf.token}', ${order.getId()})" class="text-start py-4">

                        <div class="mb-3 row">
                            <label for="orderDate" class="col-sm-4 col-form-label">Date:</label>
                            <div class="col-sm-8">
                                <div id="dateAlert" class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                    <strong id="dateError"></strong>
                                </div>

                                <input id="orderDate" type="date" class="form-control" required value="${order.getHtmlDate()}" onchange="closeAlertWindow()">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="orderTime" class="col-sm-4 col-form-label">Time:</label>
                            <div class="col-sm-8">
                                <div id="timeAlert" class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                    <strong id="timeError"></strong>
                                </div>

                                <input id="orderTime" type="time" class="form-control" required value="${order.getTime()}" onchange="closeAlertWindow()">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="address" class="col-sm-4 col-form-label">Address:</label>
                            <div class="col-sm-8">
                                <div id="addressAlert" class="alert alert-admin alert-danger alert-dismissible fade show d-none" role="alert">
                                    <strong id="addressError"></strong>
                                </div>

                                <input id="address" type="text" placeholder="Input your address" class="form-control" required value="${order.getAddress()}" onchange="closeAlertWindow()">
                            </div>
                        </div>

                        <a role="button" class="btn btn-outline-secondary mt-3"
                           href="/order">Cancel</a>

                        <button type="submit" class="btn black-btn-outline mt-3">Save</button>
                    </form>
                </div>
            </section>
        </div>
    </section>

</@c.page>