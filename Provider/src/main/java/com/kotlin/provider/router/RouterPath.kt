package com.kotlin.provider.router
object RouterPath{
    class UserCenter{
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
        }
    }

    class OrderCenter{
        companion object {
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
        }
    }

    class PaySDK{
        companion object {
            const val PATH_PAY = "/paySDK/pay"
        }
    }

    class MessageCenter{
        companion object {
            const val PATH_MESSAGE_PUSH = "/message/push"
            const val PATH_MESSAGE_ORDER = "/messageCenter/order"
        }
    }
}
