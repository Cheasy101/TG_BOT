package org.example;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    private boolean screaming = false;
    final static Boolean flag = true;
    static ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    protected Message recievedMessage;

//увидел в ютубе реализацию

    private enum options {
        ARGUMENTS_INPUT, WORK, CALLBACKQUERY
    }

    private options option = options.WORK;

    //    private options mode = options.WORK;
    @Override

    public String getBotUsername() {
        return "topG1_bot";
    }

    @Override
    public String getBotToken() {
        return "5988048325:AAFpBKECVwB0Q1gLmzDBr1HVar-mdcMiNGY";
    }

    private InlineKeyboardMarkup keyboardM1;
    private InlineKeyboardMarkup keyboardM2;

    static

    int counter = 0;
//    AfterMath afterMath = new AfterMath();

    @Override
    public void onUpdateReceived(Update update) {
//        synchronized (flag) {
//            if (update.hasMessage()) { // чтобы метод онАпдейт понимал, с чем мы работаем, сначала нужна проверка не пустое ли сообщение вообще
//                var msg = update.getMessage();
//                var user = msg.getFrom();
//                var id = user.getId();
//                AfterMath.idForMath = id;
//
//                recievedMessage = msg; // TODO: 01.04.2023 проверить
//
//
//                var txt = msg.getText();
//                if (msg.isCommand()) { // обработчик для команд
//                    switch (txt) {
//                        case "/start" -> {
//                            try {
//                                startGreetingAnswer(user);
//                                option = options.WORK;
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            }
//
//                            // TODO: 30.03.2023  добавить кнопки в меню
//                        }
//                        case "/scream" -> screaming = true;
//                        case "/whisper" -> screaming = false;
//                        case "/operate" -> operateFunction(id, "выберите номер варика");
//                        default -> sendText(id, "что за команда? я такой не знаю");
//                    }
//                }
//                if (option == options.ARGUMENTS_INPUT) {
//                    recievedMessage = update.getMessage() == null ? new Message() : update.getMessage();
//                    //строка присваивает переменной recievedMessage значение полученного сообщения,
//                    // если оно не равно null, иначе создает новый объект сообщения и присваивает его переменной
//                    System.out.println("Обновился message: " + recievedMessage.getText());
//                    flag.notify();
//                    try {
//                        flag.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                } else if (option == options.WORK)
////                (msg.isUserMessage())
//                {
//                    if (txt.equals("Вычислить функцию")) {//                    sendText(id, "Выберите вариант, пожалуйста");
//                        operateFunction(id, "Выберите номер варианта, пожалуйста");
//                    }
//                }
//            } else if (update.hasCallbackQuery()) {
//
//                AfterMath.updateForMath = update;
//
//                String callbackData = update.getCallbackQuery().getData();
//                long messageId = update.getCallbackQuery().getMessage().getMessageId();
//                long chatId = update.getCallbackQuery().getMessage().getChatId();
//
//                option = options.ARGUMENTS_INPUT;
//                new Thread(new AfterMath(callbackData)).start(); // создаем объект сразу с нужным айди
//
//                //                    thread.start();
//
////                    synchronized (flag) {  //пропустит только 1 поток
////                        System.out.println("синхронизировались");
//                switch (callbackData) {
//                    case "button1" -> {
//                        String text = "Вы выбрали первый вариант, теперь введите переменные, пожалуйста";
//                        executeEditMessageText(text, chatId, messageId);
//                    }
//                    case "button2" -> {
//                        String text = "Вы выбрали второй вариант, теперь введите переменные, пожалуйста";
//                        executeEditMessageText(text, chatId, messageId);
//                        counter = 2;
//                    }
//                    case "button3" -> {
//                        String text = "Вы выбрали третий вариант, теперь введите переменные, пожалуйста";
//                        executeEditMessageText(text, chatId, messageId);
//                    }
//                    case "button4" -> {
//                        String text = "Вы выбрали четвертый вариант, теперь введите переменные, пожалуйста";
//                        executeEditMessageText(text, chatId, messageId);
//                    }
//                    case "button5" -> {
//                        String text = "Вы выбрали пятый вариант, теперь введите переменные, пожалуйста";
//                        executeEditMessageText(text, chatId, messageId);
//                    }
//                    case "button6" -> {
//                        String text = "Вы выбрали шестой вариант, теперь введите переменные, пожалуйста";
//                        executeEditMessageText(text, chatId, messageId);
//                    }
//                    case "button7" -> {
//                        String text = "Вы выбрали седьмой вариант, теперь введите переменные, пожалуйста";
//                        executeEditMessageText(text, chatId, messageId);
//                    }
//                }
//            }
//        }
        synchronized (flag) {
            if (update.hasMessage()) {
                if (update.getMessage().isCommand())
//            (option == options.WORK)
                {
                    var msg = update.getMessage();
                    var user = msg.getFrom();
                    var id = user.getId();
                    AfterMath.idForMath = id;
                    var txt = msg.getText();
//                if(msg.isCommand()) {
//                }
                    // TODO: 02.04.2023 возможно надо исправить немного
                    switch (txt) {
                        case "/start" -> {
                            try {
                                startGreetingAnswer(user);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case "/scream" -> screaming = true;
                        case "/whisper" -> screaming = false;
                        case "/operate", "Вычислить функцию" -> operateFunction(id, "выберите номер варика");
                        default -> sendText(id, "что за команда? я такой не знаю");
                    }
                }
                else if (option == options.ARGUMENTS_INPUT) {

                    recievedMessage = update.getMessage() == null ? new Message() : update.getMessage();
                    //строка присваивает переменной recievedMessage значение полученного сообщения,
                    // если оно не равно null, иначе создает новый объект сообщения и присваивает его переменной
                    System.out.println("Обновился message: " + recievedMessage.getText());
                    flag.notify();
                    try {
                        flag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

//            else if (option == options.CALLBACKQUERY) {

            if (update.hasCallbackQuery()) {




                option = options.ARGUMENTS_INPUT; //переключаемся на прием аргументов

                AfterMath.updateForMath = update;

                String callbackData = update.getCallbackQuery().getData();
                long messageId = update.getCallbackQuery().getMessage().getMessageId();
                long chatId = update.getCallbackQuery().getMessage().getChatId();


                //                    thread.start();

//                    synchronized (flag) {  //пропустит только 1 поток
//                        System.out.println("синхронизировались");
                switch (callbackData) {
                    case "button1" -> {
                        String text = "Вы выбрали первый вариант, теперь введите переменные, пожалуйста";
                        executeEditMessageText(text, chatId, messageId);
                    }
                    case "button2" -> {
                        String text = "Вы выбрали второй вариант, теперь введите переменные, пожалуйста";
                        executeEditMessageText(text, chatId, messageId);
                    }
                    case "button3" -> {
                        String text = "Вы выбрали третий вариант, теперь введите переменные, пожалуйста";
                        executeEditMessageText(text, chatId, messageId);
                    }
                    case "button4" -> {
                        String text = "Вы выбрали четвертый вариант, теперь введите переменные, пожалуйста";
                        executeEditMessageText(text, chatId, messageId);
                    }
                    case "button5" -> {
                        String text = "Вы выбрали пятый вариант, теперь введите переменные, пожалуйста";
                        executeEditMessageText(text, chatId, messageId);
                    }
                    case "button6" -> {
                        String text = "Вы выбрали шестой вариант, теперь введите переменные, пожалуйста";
                        executeEditMessageText(text, chatId, messageId);
                    }
                    case "button7" -> {
                        String text = "Вы выбрали седьмой вариант, теперь введите переменные, пожалуйста";
                        executeEditMessageText(text, chatId, messageId);
                    }
                }
                new Thread(new AfterMath(callbackData)).start(); // создаем объект сразу с нужным айди
            }
//        }

        }
    }


//    private double parseMessage(String text, long chatId) throws InterruptedException {
//        double result;
//        synchronized (flag) {
//            while (true) {
//                try {
//                    result = Double.parseDouble(text);
//                    break;
//                } catch (NumberFormatException ignored) {
//                    sendText(chatId, "попробуйте ввести значения еще раз");
//                    wait();
//                }
//            }
//            return result;
//        }
//    }

    private void executeEditMessageText(String text, long chatId, long messageId) {
        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.setMessageId((int) messageId);

        try {
            execute(message);
        } catch (TelegramApiException ignored) {
        }
    }

    private void operateFunction(Long id, String whatWeSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(id));
        sendMessage.setText(whatWeSend);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var button1 = new InlineKeyboardButton();
        var button2 = new InlineKeyboardButton();
        var button3 = new InlineKeyboardButton();
        var button4 = new InlineKeyboardButton();
        var button5 = new InlineKeyboardButton();
        var button6 = new InlineKeyboardButton();
        var button7 = new InlineKeyboardButton();

        button1.setText(EmojiParser.parseToUnicode(":one:"));
        button2.setText(EmojiParser.parseToUnicode(":two:"));
        button3.setText(EmojiParser.parseToUnicode(":three:"));
        button4.setText(EmojiParser.parseToUnicode(":four:"));
        button5.setText(EmojiParser.parseToUnicode(":five:"));
        button6.setText(EmojiParser.parseToUnicode(":six:"));
        button7.setText(EmojiParser.parseToUnicode(":seven:"));

        button1.setCallbackData("button1");
        button2.setCallbackData("button2");
        button3.setCallbackData("button3");
        button4.setCallbackData("button4");
        button5.setCallbackData("button5");
        button6.setCallbackData("button6");
        button7.setCallbackData("button7");

        rowInLine.add(button1);
        rowInLine.add(button2);
        rowInLine.add(button3);
        rowInLine.add(button4);
        rowInLine.add(button5);
        rowInLine.add(button6);
        rowInLine.add(button7);

        rowsInLine.add(rowInLine);
        markupInLine.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(markupInLine);

        // TODO: 02.04.2023 меняем состояние на указание варианта из inlineKeyBoard
        option = options.CALLBACKQUERY;

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    private void startGreetingAnswer(User user) throws InterruptedException {
        if (user.getFirstName().equals("Булат") & user.getLastName().equals("Алексеевич")) {
            String emojiParser = EmojiParser.parseToUnicode("Приветствую вас, босс :kissing_closed_eyes::kissing_closed_eyes:\n вы прекрасно выглядите сегодня");
            sendText(user.getId(), emojiParser);
//        user.wait(1); todo научить бота отвечать как челвоек. чтоб помедленнее сообщения писал
            sendText(user.getId(), "Какую функцию вы бы хотели использовать? ");
        } else {
            String emojiParser = EmojiParser.parseToUnicode("Приветствую вас, " + user.getFirstName() + " " + user.getLastName() + " :kissing_closed_eyes::kissing_closed_eyes:");
            sendText(user.getId(), emojiParser);
//        user.wait(1); todo научить бота отвечать как челвоек. чтоб помедленнее сообщения писал
            sendText(user.getId(), "Какую функцию вы бы хотели использовать? ");
        }
    }


    public void sendText(Long who, String what) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(who.toString()) // Who are we sending a message to
                .text(what).build();   // Message content


        KeyboardRow row = new KeyboardRow(); // тут типа создал объект, куда передадим основные ф-ии
        row.add("Вычислить функцию");

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(row);

        addReplyButtons(replyKeyboardMarkup, keyboardRows); //вызвал метод, который создает лист, добавляет туда вот эти функции
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static ReplyKeyboard addReplyButtons(ReplyKeyboardMarkup replyKeyboardMarkup, List<KeyboardRow> keyboardRows) {
        //какой ряд добавляем первым, такой и будет кароче
        replyKeyboardMarkup.setKeyboard(keyboardRows);//установили клавиатуру, которую будем передавать
        return replyKeyboardMarkup;
    }

    public void scream(Long who, Message msg) {
        if (screaming) {
            sendText(who, msg.getText().toUpperCase());
        } else copyMessage(who, msg.getMessageId());
    }

    public void copyMessage(Long who, Integer msgID) {
        CopyMessage copyMessage = CopyMessage.builder()
                .fromChatId(who.toString()) //from who
                .chatId(who.toString()) //to himself
                .messageId(msgID).build(); //what
        try {
            execute(copyMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    public class AfterMath implements Runnable {
        int answer = 0;
        public String variant;

        public AfterMath(String variant) {
            this.variant = variant;
        }

        public void setVariant(String variant) {
            this.variant = variant;
        }

        static Update updateForMath;
        static long idForMath;
        // TODO: 01.04.2023 возможно будет ошибка


//    public double getAnswer(int variant) {
//        // надо как то получать значения с бота
//        Bot bot = new Bot();
//
//        switch (variant) {
//            case 1:
//                bot.sendText(idForMath, "Введите значения для переменных");
//                bot.sendText(idForMath, "Переменная а");
//
//                System.out.println(updateForMath.getMessage().getText());
//
////                onUpdatesReceived(Collections.singletonList(updateForMath));
//
//
//                int a = 1;
//                int b = 1; // TODO: 31.03.2023 реализовать
//                int c = 1;
//                int x = 1;
//                int n = 1;
//
//                double answer = (Math.pow(5 * a, n * x) / b + c) - Math.sqrt(Math.abs(Math.cos(Math.pow(x, 3))));
//                break;
//            case 2:
//
//        }
//        return answer;
//    }

//        @Override
//        public String getBotUsername() {
//            return "topG1_bot";
//        }
//
//        @Override
//        public String getBotToken() {
//            return "5988048325:AAFpBKECVwB0Q1gLmzDBr1HVar-mdcMiNGY";
//        }

        public void notifyThenWait() {
            try {
                flag.notify();
                flag.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public synchronized void run() {
            try {
                synchronized (flag) {
                    switch (variant) {
                        case "button1": {
                            sendText(idForMath, "Введите значения для переменных");
                            sendText(idForMath, "Переменная а");
                            notifyThenWait();
                            double a = parseReceivedMessage();

                            sendText(idForMath, "Переменная b");
                            notifyThenWait();
                            double b = parseReceivedMessage(); // TODO: 31.03.2023 реализовать

                            sendText(idForMath, "Переменная c");
                            notifyThenWait();
                            double c = parseReceivedMessage();

                            sendText(idForMath, "Переменная x");
                            notifyThenWait(); double x = parseReceivedMessage();

                            sendText(idForMath, "Переменная n");
                            flag.notify();
                            flag.wait();
                            double n = parseReceivedMessage();

                            double answer = (Math.pow(5 * a, n * x) / b + c) - Math.sqrt(Math.abs(Math.cos(Math.pow(x, 3))));
                            sendText(idForMath, "ваш ответ -> " + answer);
                            break;
                        }
                        case "button2": {
                            sendText(idForMath, "A:");
                            notifyThenWait();
                            double a = parseReceivedMessage();

                            sendText(idForMath, "W:");
                            notifyThenWait();
                            double w = parseReceivedMessage();

                            sendText(idForMath, "X:");
                            notifyThenWait();
                            double x = parseReceivedMessage();

                            sendText(idForMath, "Y:");
                            notifyThenWait();
                            double y = parseReceivedMessage();

                            double answer = (Math.abs(x - y) / Math.pow(1 + 2 * x, a) - Math.pow(Math.E, Math.sqrt(1 + w)));
                            sendText(idForMath, "Выражение = " + answer);
                            break;
                        }
                        case "button3": {
                            sendText(idForMath, "A0:");
                            notifyThenWait();
                            double a0 = parseReceivedMessage();

                            sendText(idForMath, "A1:");
                            notifyThenWait();
                            double a1 = parseReceivedMessage();

                            sendText(idForMath, "A2:");
                            notifyThenWait();
                            double a2 = parseReceivedMessage();

                            sendText(idForMath, "X:");
                            notifyThenWait();
                            double x = parseReceivedMessage();

                            double answer = (Math.sqrt(a0 + a1 * x + a2 * Math.pow(Math.abs(Math.sin(x)), 1 / 3f)));
                            sendText(idForMath, "ваш ответ -> " + answer);
                            break;
                        }
                        case "button4": {
                            sendText(idForMath, "A:");
                            notifyThenWait();
                            double a = parseReceivedMessage();

                            sendText(idForMath, "X:");
                            notifyThenWait();
                            double x = parseReceivedMessage();

                            double answer = (Math.log(Math.abs(Math.pow(a, 7))) + Math.atan(x * x) + Math.PI / Math.sqrt(Math.abs(a + x)));
                            sendText(idForMath, "ваш ответ -> " + answer);
                            break;
                        }
                        case "button5": {
                            sendText(idForMath, "A:");
                            double a = parseReceivedMessage();

                            notifyThenWait();
                            sendText(idForMath, "B:");
                            double b = parseReceivedMessage();

                            notifyThenWait();
                            sendText(idForMath, "C:");
                            double c = parseReceivedMessage();

                            notifyThenWait();
                            sendText(idForMath, "D:");
                            double d = parseReceivedMessage();

                            notifyThenWait();
                            sendText(idForMath, "X:");
                            double x = parseReceivedMessage();

                            notifyThenWait();
                            double answer = (Math.pow(Math.pow(a + b, 2) / (c + d) + Math.pow(Math.E, Math.sqrt(x + 1)), 1 / 5f));
                            sendText(idForMath, "ваш ответ -> " + answer);
                            break;
                        }
                        case "button6": {
                            sendText(idForMath, "X:");
                            notifyThenWait();
                            double x = parseReceivedMessage();

                            double answer = (Math.pow(Math.E, (2 * Math.sin(4 * x) + Math.pow(Math.cos(x * x), 2)) / (3 * x)));
                            sendText(idForMath, "ваш ответ -> " + answer);
                            break;
                        }
                        case "button7": {
                            sendText(idForMath, "X:");
                            notifyThenWait();
                            double x = parseReceivedMessage();

                            double answer = (0.25 * ((1 + x * x) / (1 - x) + 0.5 * Math.tan(x)));
                            sendText(idForMath, "ваш ответ -> " + answer);
                            break;
                        }
                    }
                    option = options.WORK;
                    flag.notify();
//                    voidMessage("");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        public double parseReceivedMessage() throws InterruptedException {
            double answer;
            synchronized (flag) {
                while (true) {
                    String str = recievedMessage.getText();
                    try {
                        answer = Double.parseDouble(str);
                        break;
                    } catch (NumberFormatException e) {
                        sendText(AfterMath.idForMath, "Неправильно введенные данные, попробуйте еще раз");
                        flag.notify();
                        flag.wait();
                    }
                }
            }
            return answer;
        }
    }

    public void voidMessage(String text) {
        Message message = new Message();
        message.setText(text);
        Update update = new Update();
        update.setMessage(message);
        onUpdateReceived(update);
    }
}

