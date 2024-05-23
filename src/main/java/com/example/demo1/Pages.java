package com.example.demo1;

public class Pages {
    ////////////// نام کاربری، ساعت و نام صفحه , نام تمامی صفحات///////////


    public interface Page {

        void displayPage();
    }

    public class LoginPage implements Page {
        @Override
        public void displayPage() {
            //  login page with username, password, and CAPTCHA fields
        }
    }

    public class HomePage implements Page {
        @Override
        public void displayPage() {
            //  home page showing all token and their information
            ///click tartib
            ///click ==token page
        }
    }



    public class CurrencyPage implements Page {
        @Override
        public void displayPage() {
            // token page with real-time price, transaction, daily change percentage, and price nemoodar
        }
    }

    public class ExchangePage implements Page {

        @Override
        public void displayPage() {
            ///kharid va forosh
            ///saf kharidva forosh
            //tarakonesh sabegh user ba token
            //  exchange page for buying and selling
            ///methodbuy va sell
        }
    }

    public class SwapPage implements Page {

        @Override
        public void displayPage() {
            //حجم و نوع ارزی که مد نظر  خرید یا فروش بودن
//                        همچنین قسمتی برای محاسبه مقدار معادل یک ارز با ارزی دیگرقیمت دو ارز
//                برای کاربر نمایش داده میشود. در صورتی که کاربر گزینه تایید را بزند، پس از پرداخت کارمزد به صرافی، تبادل انجام
//                خواهد شد
            // Implement the swap page for exchanging a specific amount of one currency for another
        }
    }


    public class ProfilePage implements Page {

        @Override
        public void displayPage() {


            //////ویرایش به جز نام کاربری / ورود به کیف / تاریخچه
        }
    }

    public class WalletPage implements Page {
        @Override
        public void displayPage() {
// دارایی فعلی فرد به ارز پایه
// نمودار ساالنه دارایی
// ارز های فرد به همراه قیمت و مقدار

        }
    }

    public class HistoryPage implements Page {

        @Override
        public void displayPage() {

//             معامالت انجام شده نوع، مقدار و قیمت ارزو تاریخ
//            کاربر به بازار درخواست داده است  قبول نشده اند pending
//    کاربر با انتخاب گزینه دریافت فایل export
        }
    }

    public class TransferPage implements Page {


        @Override
        public void displayPage() {
            //     وارد کردن id_wallet یک کاربر و مشخص کردن ارز مورد نظر و مقدار آن، به کیف پول آن
        }
    }
}
