package org.example;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    private boolean screaming = false;
    final static Boolean flag = true;
    static ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    private static int variable;


    protected Message recievedMessage;

//увидел в ютубе реализацию через енамы

    private enum options {
        ARGUMENTS_INPUT, WORK, IN1STR, input1StrArgs, CALLBACKQUERY
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

    @Override
    public void onUpdateReceived(Update update) {

        synchronized (flag) {
            if (update.hasMessage()) {
                if (update.getMessage().isCommand()) {
                    var msg = update.getMessage();
                    var user = msg.getFrom();
                    var id = user.getId();
                    AfterMath.idForMath = id;
                    var txt = msg.getText();
                    switch (txt) {
                        case "/start" -> {
                            try {
                                startGreetingAnswer(user);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case "/operate" -> {
                            operateFunction(id, "выберите номер варика");
                            sendPhoto(id);
                        }
                        case "/parsestr" -> {
                            sendText(id, "Выбери номер варианта. Всего их 7. От 1 до 7" +
                                    "\n Вон, даже картиночка есть");
                            sendPhoto(id);
                            option = options.IN1STR;
                        }
                        default -> sendText(id, "что за команда? я такой не знаю");
                    }
                } else if (option == options.ARGUMENTS_INPUT) {
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
                } else if (update.getMessage().getText().equals("Вычислить функцию")) {
                    operateFunction(update.getMessage().getFrom().getId(), "выберите номер варика");
                    sendPhoto(update.getMessage().getFrom().getId());
                    AfterMath.idForMath = update.getMessage().getFrom().getId();
                    option = options.CALLBACKQUERY;
                } else if (option == options.IN1STR) {
                    var user = update.getMessage().getFrom();
                    Long id = user.getId();
                    String varik = update.getMessage().getText();
                    sendText(id, "Че за анаконда");


                    switch (varik) {
                        case "1" -> {
                            sendText(id, """
                                    Вы выбрали первый вариант 1: Вводите аргументы\s
                                    Тут надо 5 констант в 1 строку через пробел\\n" +
                                                                        " в формате => a b c x n\s""");
                            variable = 1;
                        }
                        case "2" -> {
                            sendText(id, """
                                    Вы выбрали первый вариант 2: Вводите аргументы\s
                                    надо 4 константы в формате\s
                                     => A W(омега) X Y""");
                            variable = 2;
                        }
                        case "3" -> {
                            sendText(id, """
                                    Вы выбрали первый вариант 3: Вводите аргументы\s
                                    надо 4 константы в формате\s
                                     => A0 A1 A2 X""");
                            variable = 3;
                        }
                        case "4" -> {
                            sendText(id, """
                                    Вы выбрали первый вариант 4: Вводите аргументы\s
                                    надо 4 константы в формате\s
                                     => A X""");
                            variable = 4;
                        }
                        case "5" -> {
                            sendText(id, """
                                    Вы выбрали первый вариант 5: Вводите аргументы\s
                                    надо 4 константы в формате\s
                                     => A B C D X""");
                            variable = 5;
                        }
                        case "6" -> {
                            sendText(id, """
                                    Вы выбрали первый вариант 6: Вводите аргументы\s
                                    надо 4 константы в формате\s
                                     => X""");
                            variable = 6;
                        }
                        case "7" -> {
                            sendText(id, """
                                    Вы выбрали первый вариант 6: Вводите аргументы\s
                                    надо 4 константы в формате\s
                                     => X""");
                            variable = 7;
                        }
                    }
                    option = options.input1StrArgs; // TODO: 03.04.2023 мысль - стат константа варианта ! йоу
                } else if (option == options.input1StrArgs) {
                    String[] variables;
                    Long id = update.getMessage().getFrom().getId();

                    switch (variable) {
                        case 1 -> {
                            try {
                                variables = update.getMessage().getText().split(" ");
                                if (variables.length != 5) {
                                    throw new Exception("Ошибочка вышла! ");
                                }
                                int a = Integer.parseInt(variables[0]);
                                int b = Integer.parseInt(variables[1]);
                                int c = Integer.parseInt(variables[2]);
                                int x = Integer.parseInt(variables[3]);
                                int n = Integer.parseInt(variables[4]);
                                double answer = (Math.pow(5 * a, n * x) / b + c) - Math.sqrt(Math.abs(Math.cos(Math.pow(x, 3))));
                                sendText(id, "ваш ответ -> " + answer);
                            } catch (Exception e) {
                                sendText(id, "неправильно ты данные вводишь, еще раз попробуй");
                            }
                        }
                        case 2 -> {
                            try {
                                variables = update.getMessage().getText().split(" ");
                                if (variables.length != 4) {
                                    throw new Exception("Ошибочка вышла! ");
                                }
                                int a = Integer.parseInt(variables[0]);
                                int w = Integer.parseInt(variables[1]);
                                int x = Integer.parseInt(variables[2]);
                                int y = Integer.parseInt(variables[3]);
                                double answer = (Math.abs(x - y) / Math.pow(1 + 2 * x, a) - Math.pow(Math.E, Math.sqrt(1 + w)));
                                sendText(id, "ваш ответ -> " + answer);
                            } catch (Exception e) {
                                sendText(id, "неправильно ты данные вводишь, еще раз попробуй");
                            }
                        }
                        case 3 -> {
                            try {
                                variables = update.getMessage().getText().split(" ");
                                if (variables.length != 4) {
                                    throw new Exception("Ошибочка вышла! ");
                                }
                                int a0 = Integer.parseInt(variables[0]);
                                int a1 = Integer.parseInt(variables[1]);
                                int a2 = Integer.parseInt(variables[2]);
                                int x = Integer.parseInt(variables[3]);
                                double answer = (Math.sqrt(a0 + a1 * x + a2 * Math.pow(Math.abs(Math.sin(x)), 1 / 3f)));
                                sendText(id, "ваш ответ -> " + answer);
                            } catch (Exception e) {
                                sendText(id, "неправильно ты данные вводишь, еще раз попробуй");
                            }
                        }
                        case 4 -> {
                            try {
                                variables = update.getMessage().getText().split(" ");
                                if (variables.length != 2) {
                                    throw new Exception("Ошибочка вышла! ");
                                }
                                int a = Integer.parseInt(variables[0]);
                                int x = Integer.parseInt(variables[1]);
                                double answer = (Math.log(Math.abs(Math.pow(a, 7))) + Math.atan(x * x) + Math.PI / Math.sqrt(Math.abs(a + x)));
                                sendText(id, "ваш ответ -> " + answer);
                            } catch (Exception e) {
                                sendText(id, "неправильно ты данные вводишь, еще раз попробуй");
                            }
                        }
                        case 5 -> {
                            try {
                                variables = update.getMessage().getText().split(" ");
                                if (variables.length != 5) {
                                    throw new Exception("Ошибочка вышла! ");
                                }
                                int a = Integer.parseInt(variables[0]);
                                int b = Integer.parseInt(variables[1]);
                                int c = Integer.parseInt(variables[2]);
                                int d = Integer.parseInt(variables[3]);
                                int x = Integer.parseInt(variables[4]);
                                double answer = (Math.pow(Math.pow(a + b, 2) / (c + d) + Math.pow(Math.E, Math.sqrt(x + 1)), 1 / 5f));
                                sendText(id, "ваш ответ -> " + answer);
                            } catch (Exception e) {
                                sendText(id, "неправильно ты данные вводишь, еще раз попробуй");
                            }
                        }
                        case 6 -> {
                            try {
                                variables = update.getMessage().getText().split(" ");
                                if (variables.length != 1) {
                                    throw new Exception("Ошибочка вышла! ");
                                }
                                int x = Integer.parseInt(variables[0]);
                                double answer = (Math.pow(Math.E, (2 * Math.sin(4 * x) + Math.pow(Math.cos(x * x), 2)) / (3 * x)));
                                sendText(id, "ваш ответ -> " + answer);
                            } catch (Exception e) {
                                sendText(id, "неправильно ты данные вводишь, еще раз попробуй");
                            }
                        }
                        case 7 -> {
                            try {
                                variables = update.getMessage().getText().split(" ");
                                if (variables.length != 1) {
                                    throw new Exception("Ошибочка вышла! ");
                                }
                                int x = Integer.parseInt(variables[0]);
                                double answer = (0.25 * ((1 + x * x) / (1 - x) + 0.5 * Math.tan(x)));
                                sendText(id, "ваш ответ -> " + answer);
                            } catch (Exception e) {
                                sendText(id, "неправильно ты данные вводишь, еще раз попробуй");
                            }
                        }
                    }
                }

            } else if (update.hasCallbackQuery()) {


                AfterMath.updateForMath = update;

                String callbackData = update.getCallbackQuery().getData();
                long messageId = update.getCallbackQuery().getMessage().getMessageId();
                long chatId = update.getCallbackQuery().getMessage().getChatId();

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
                option = options.ARGUMENTS_INPUT; //переключаемся на прием аргументов
                new Thread(new AfterMath(callbackData)).start(); // создаем объект сразу с нужным айди
            }
        }
    }


    private void sendPhoto(long id) {


        // создаем объект InputFile из файла
        File photo = new File("C:\\Users\\Bulat\\IdeaProjects\\tgBot\\Варианты.png");
        InputFile inputFile = new InputFile(photo);
        // создаем объект SendPhoto и добавляем в него InputFile
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(id));
        sendPhoto.setPhoto(inputFile);
        // отправляем фото
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

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
                        case "button1" -> {
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
                            notifyThenWait();
                            double x = parseReceivedMessage();

                            sendText(idForMath, "Переменная n");
                            flag.notify();
                            flag.wait();
                            double n = parseReceivedMessage();

                            double answer = (Math.pow(5 * a, n * x) / b + c) - Math.sqrt(Math.abs(Math.cos(Math.pow(x, 3))));
                            sendText(idForMath, "ваш ответ -> " + answer);
                        }
                        case "button2" -> {
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
                        }
                        case "button3" -> {
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
                        }
                        case "button4" -> {
                            sendText(idForMath, "A:");
                            notifyThenWait();
                            double a = parseReceivedMessage();

                            sendText(idForMath, "X:");
                            notifyThenWait();
                            double x = parseReceivedMessage();

                            double answer = (Math.log(Math.abs(Math.pow(a, 7))) + Math.atan(x * x) + Math.PI / Math.sqrt(Math.abs(a + x)));
                            sendText(idForMath, "ваш ответ -> " + answer);
                        }
                        case "button5" -> {
                            sendText(idForMath, "A:");
                            notifyThenWait();
                            double a = parseReceivedMessage();

                            sendText(idForMath, "B:");
                            notifyThenWait();
                            double b = parseReceivedMessage();

                            sendText(idForMath, "C:");
                            notifyThenWait();
                            double c = parseReceivedMessage();

                            sendText(idForMath, "D:");
                            notifyThenWait();
                            double d = parseReceivedMessage();

                            sendText(idForMath, "X:");
                            notifyThenWait();
                            double x = parseReceivedMessage();


                            double answer = (Math.pow(Math.pow(a + b, 2) / (c + d) + Math.pow(Math.E, Math.sqrt(x + 1)), 1 / 5f));
                            sendText(idForMath, "ваш ответ -> " + answer);
                        }
                        case "button6" -> {
                            sendText(idForMath, "X:");
                            notifyThenWait();
                            double x = parseReceivedMessage();

                            double answer = (Math.pow(Math.E, (2 * Math.sin(4 * x) + Math.pow(Math.cos(x * x), 2)) / (3 * x)));
                            sendText(idForMath, "ваш ответ -> " + answer);
                        }
                        case "button7" -> {
                            sendText(idForMath, "X:");
                            notifyThenWait();
                            double x = parseReceivedMessage();

                            double answer = (0.25 * ((1 + x * x) / (1 - x) + 0.5 * Math.tan(x)));
                            sendText(idForMath, "ваш ответ -> " + answer);
                        }
                    }
                    option = options.WORK;
                    flag.notify();
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
}

