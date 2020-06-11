package cn.wildfirechat.message;

import android.annotation.SuppressLint;
import android.os.Parcel;

import cn.wildfirechat.message.core.ContentTag;
import cn.wildfirechat.message.core.MessagePayload;
import cn.wildfirechat.message.core.PersistFlag;

import static cn.wildfirechat.message.core.MessageContentType.ContentType_Redpack;

@SuppressLint("ParcelCreator")
@ContentTag(type = ContentType_Redpack, flag = PersistFlag.Persist_And_Count)
public class RedpackMessageContent extends MessageContent {
    private String content;

    public RedpackMessageContent(){

    }

    public RedpackMessageContent(String content){
        this.content = content;
    }

    public String getContent(){ return content; }

    public  void setContent(String content) { this.content = content;}

    @Override
    public MessagePayload encode(){
        MessagePayload payload = new MessagePayload();
        payload.searchableContent = content;
        payload.mentionedType = mentionedType;
        payload.mentionedTargets = mentionedTargets;
        return  payload;
    }

    @Override
    public  void decode(MessagePayload payload){
        content = payload.searchableContent;
        mentionedType = payload.mentionedType;
        mentionedTargets = payload.mentionedTargets;
    }

    @Override
    public String digest(Message message) {
        return "[红包]";
    }

    @Override
    public int describeContents(){return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags){
        super.writeToParcel(dest, flags);
        dest.writeString(this.content);
    }

    protected  RedpackMessageContent(Parcel in){
        super(in);
        this.content = in.readString();
    }

    public  static  final Creator<RedpackMessageContent> CREATOR = new Creator<RedpackMessageContent>() {
        @Override
        public RedpackMessageContent createFromParcel(Parcel source) {
            return new RedpackMessageContent(source);
        }

        @Override
        public RedpackMessageContent[] newArray(int size) {
            return new RedpackMessageContent[size];
        }
    };
}
