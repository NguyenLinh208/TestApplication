package jp.linhnk.myapplication.api;

public interface ApiObjectCallback<T> {

    void onSuccess(T response);

    void onFail(int errorCode, String message);

}
