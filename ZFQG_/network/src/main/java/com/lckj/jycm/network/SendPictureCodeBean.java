package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

public class SendPictureCodeBean extends HttpResponse {

    /**
     * msg : null
     * data : {"img_id":"C29HVH_","img_io":"iVBORw0KGgoAAAANSUhEUgAAAHgAAAArCAIAAAA8HNvcAAABzklEQVR42u3bMW7DMAwFUB0ia47RvdfooTJ17trDdMiV3EyGIdLMJ0XZZEJBSxLJAR4EgmKYttQ4ZLQiKOiCrlHQBV3QNQq6oA8a948/dsq7Pr+udBa0wlcWZ33jcLe8yltrRPlc6xZcGVkjawaxbmGV8cUI4unWLaPydhfOV9BG5RHo463DQas2buGQvW8NbVam0KoUu/vo+v1DZyzo39tlnYZlHTT4NC9o1ncGtzO0oINAg49ygX5MRNmL2yd0PNXpBNdlI8eZhZat96DZxQXtCS0/39H6raGRr4gLTY2o8rpsJEBrw7T5Lu5i7ZbeIdD05R70jAyvoN2ghWvLSCWvoKGjCpZMZyd5njdDVoq+uX2HhTZ8NV6M7l6qUukc0OxKL2i2Ks2eX/OFJTc0Gz1GqiVPQ4SgnCNGB4EGI4zqnIaDptZCNNiLHlPrfyx0sqzDDD1YKVXtLWj7Lyy2fg9b3AgKLefF3QLzb4YGZdUhDQot1I98rbtMQ1tLMuQbrwNNrbV9HapLoywYOo8eh15MnUq2NiX8hvKa0Iu+987cCQbeA4NCq+r38kq8w1HgVmVvLGhc6HnDnAiePvI1oie1Ttnxn9E6618r0ln/A+IyB7jPYHuwAAAAAElFTkSuQmCC"}
     * token : null
     * code : null
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
         * img_id : C29HVH_
         * img_io : iVBORw0KGgoAAAANSUhEUgAAAHgAAAArCAIAAAA8HNvcAAABzklEQVR42u3bMW7DMAwFUB0ia47RvdfooTJ17trDdMiV3EyGIdLMJ0XZZEJBSxLJAR4EgmKYttQ4ZLQiKOiCrlHQBV3QNQq6oA8a948/dsq7Pr+udBa0wlcWZ33jcLe8yltrRPlc6xZcGVkjawaxbmGV8cUI4unWLaPydhfOV9BG5RHo463DQas2buGQvW8NbVam0KoUu/vo+v1DZyzo39tlnYZlHTT4NC9o1ncGtzO0oINAg49ygX5MRNmL2yd0PNXpBNdlI8eZhZat96DZxQXtCS0/39H6raGRr4gLTY2o8rpsJEBrw7T5Lu5i7ZbeIdD05R70jAyvoN2ghWvLSCWvoKGjCpZMZyd5njdDVoq+uX2HhTZ8NV6M7l6qUukc0OxKL2i2Ks2eX/OFJTc0Gz1GqiVPQ4SgnCNGB4EGI4zqnIaDptZCNNiLHlPrfyx0sqzDDD1YKVXtLWj7Lyy2fg9b3AgKLefF3QLzb4YGZdUhDQot1I98rbtMQ1tLMuQbrwNNrbV9HapLoywYOo8eh15MnUq2NiX8hvKa0Iu+987cCQbeA4NCq+r38kq8w1HgVmVvLGhc6HnDnAiePvI1oie1Ttnxn9E6618r0ln/A+IyB7jPYHuwAAAAAElFTkSuQmCC
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
