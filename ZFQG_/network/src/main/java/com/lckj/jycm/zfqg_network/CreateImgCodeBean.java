package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class CreateImgCodeBean extends HttpResponse {

    /**
     * data : {"img_id":"20190820155440463991928","img_io":"iVBORw0KGgoAAAANSUhEUgAAAG4AAAAoCAIAAACQOFjdAAABKUlEQVR42u3ZQRLCIAwF0LrxBN7Gc+nOtQsv4qG8gKfQhTMdRiiTkIRQ8jPZlFLavkLp0OWDUIoFBKAEJSgRoAQlKEHJjtf5rZJpm8frIc3ieZ+3+5qU63xcTsIEZWDKP8ctzQkp6dDEQ4wouQFKK0r/GZxLWXQsappSShxHpMw3+1AKHUGp5jgEZT6iK2MclIwuWSk0olRx3AdlWq5OqeXoT1l5LXagVHQE5SyU9U/I4l5FSl3HgSiJFbQo1R2DUlo4elJSltTyOqBs6ZLFanJKI8eBeiUl5ZR2jm6UDY6/lFCaOgailKzpjktJmXC26mtRWty1MyWxQSFlB8cQlH0cHSi5o1tI2c3RmZLVZk5JSfnPW/oDAOU+KZsd18PDUcYMUIISlBPHF9Aas3VPvPowAAAAAElFTkSuQmCC"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * img_id : 20190820155440463991928
         * img_io : iVBORw0KGgoAAAANSUhEUgAAAG4AAAAoCAIAAACQOFjdAAABKUlEQVR42u3ZQRLCIAwF0LrxBN7Gc+nOtQsv4qG8gKfQhTMdRiiTkIRQ8jPZlFLavkLp0OWDUIoFBKAEJSgRoAQlKEHJjtf5rZJpm8frIc3ieZ+3+5qU63xcTsIEZWDKP8ctzQkp6dDEQ4wouQFKK0r/GZxLWXQsappSShxHpMw3+1AKHUGp5jgEZT6iK2MclIwuWSk0olRx3AdlWq5OqeXoT1l5LXagVHQE5SyU9U/I4l5FSl3HgSiJFbQo1R2DUlo4elJSltTyOqBs6ZLFanJKI8eBeiUl5ZR2jm6UDY6/lFCaOgailKzpjktJmXC26mtRWty1MyWxQSFlB8cQlH0cHSi5o1tI2c3RmZLVZk5JSfnPW/oDAOU+KZsd18PDUcYMUIISlBPHF9Aas3VPvPowAAAAAElFTkSuQmCC
         */

        private String img_id;
        private String img_io;

        public String getImg_id() {
            return img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }

        public String getImg_io() {
            return img_io;
        }

        public void setImg_io(String img_io) {
            this.img_io = img_io;
        }
    }
}
