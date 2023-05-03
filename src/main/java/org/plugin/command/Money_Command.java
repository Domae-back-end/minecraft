package org.plugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.plugin.model.User;
import org.plugin.service.Message_Service;
import org.plugin.service.Money_Service;

import java.util.ArrayList;

public class Money_Command implements CommandExecutor {

    Message_Service message = new Message_Service();
    Money_Service money_service = new Money_Service();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        Player p = (Player) commandSender;

        if(p.isOp()){
            //관리자
            if(args.length == 0){
                p.sendMessage(message.getInfo()+"/돈 확인 - 자신의 현재 잔액을 확인합니다.");
                p.sendMessage(message.getInfo()+"/돈 확인 [닉네임] - 닉네임의 잔액 확인합니다.");
                p.sendMessage(message.getInfo()+"/돈 설정 [닉네임] [설정금액] - 해당 유저의 잔액을 설정합니다.");
                p.sendMessage(message.getInfo()+"/돈 빼기 [닉네임] [설정금액] - 해당 유저의 잔액을 뺍니다.");
                p.sendMessage(message.getInfo()+"/돈 더하기 [닉네임] [설정금액] - 해당 유저의 잔액을 더합니다.");
            }else{

                if(args[0].equals("확인")){
                    if(args.length == 2){
                        //돈 확인 [닉네임]
                        int money = money_service.check_Money(args[1]);
                        p.sendMessage(message.getInfo()+"확인 결과 : "+money);
                    }else{
                        int money = money_service.check_Money(p.getName());
                        if(money != -1){
                            p.sendMessage(message.getInfo()+" 자신의 현재 잔액 : "+money);
                        }else{
                            p.sendMessage(message.getInfo()+" 오류가 발생하여 관리자에게 문의 주세요.");
                        }
                    }
                }else if(args[0].equals("설정")){
                    if(!(money_service.check_userName(args[1]))){
                        p.sendMessage(message.getInfo()+" 존재하지 않은 유저입니다.");
                        return false;
                    }
                    money_service.admin_chageMoney(args[1],Integer.parseInt(args[2]));
                    p.sendMessage(message.getInfo()+" 해당 유저의 잔액을 "+args[2]+" 원으로 바꿨습니다.");
                }else if(args[0].equals("빼기")){
                    if(!(money_service.check_userName(args[1]))){
                        p.sendMessage(message.getInfo()+" 존재하지 않은 유저입니다.");
                        return false;
                    }
                    int choice = money_service.admin_minMoney(args[1],Integer.parseInt(args[2]));
                    switch (choice){
                        case 1:{
                            int money = money_service.check_Money(p.getName());
                            p.sendMessage(message.getInfo()+" "+args[1]+" 유저의 돈 "+args[2]+" 원을 뺏어 현재 잔액이 "+money+" 만큼 남았습니다.");
                            break;
                        } case 2:{
                            p.sendMessage(message.getInfo()+" 유저의 돈이 그렇게 많지 않습니다..");
                            break;
                        } default:{
                            p.sendMessage(message.getInfo()+" 잘못된 오류입니다.");
                            break;
                        }
                    }
                }else if(args[0].equals("더하기")){
                    if(!(money_service.check_userName(args[1]))){
                        p.sendMessage(message.getInfo()+" 존재하지 않은 유저입니다.");
                        return false;
                    }
                    money_service.admin_addMoney(args[1],Integer.parseInt(args[2]));
                    p.sendMessage(message.getInfo()+" 성공적으로 "+args[2]+" 만큼 금액을 추가 하셨습니다.");
                }else{
                    p.sendMessage(message.getInfo()+" 잘못된 명령어입니다.");
                }
            }
        }else{
            //유저
            if(args.length == 0){
                p.sendMessage(message.getInfo()+"/돈 확인 - 자신의 현재 잔액을 확인합니다.");
                p.sendMessage(message.getInfo()+"/돈 보내기 [닉네임] [금액] - 다른 사람에게 돈을 보냅니다.");
                p.sendMessage(message.getInfo()+"/돈 순위 - 현재 순위를 봅니다.");
            }else{
                if(args[0].equals("확인")){
                    int money = money_service.check_Money(p.getName());
                    if(money != -1){
                        p.sendMessage(message.getInfo()+" 자신의 현재 잔액 : "+money);
                    }else{
                        p.sendMessage(message.getInfo()+" 오류가 발생하여 관리자에게 문의 주세요.");
                    }
                }else if(args[0].equals("보내기")){
                    if(!(money_service.check_userName(args[1]))){
                        p.sendMessage(message.getInfo()+" 존재하지 않은 유저입니다.");
                        return false;
                    }
                    if(money_service.user_throwMoney(p.getName(),args[1], Integer.parseInt(args[2]))){
                        p.sendMessage(message.getInfo()+" 성공적으로 "+args[2]+" 금액 만큼 보냈습니다.");
                    }else{
                        p.sendMessage(message.getInfo()+" 자신의 현재 잔액이 부족합니다.");
                    }
                }else if(args[0].equals("순위")){
                    ArrayList<User> user_list = money_service.user_winner();
                    p.sendMessage("---------------------------------------");
                    for (int i = 0; i < user_list.size(); i++) {
                        String msg = "["+(i+1)+"] 순위 : "+user_list.get(i).getUser_name()+" 님 / 잔액 : "+user_list.get(i).getUser_money()+" 원";
                        p.sendMessage(msg);
                    }
                    p.sendMessage("---------------------------------------");
                }else{
                    p.sendMessage(message.getInfo()+" 잘못된 명령어입니다.");
                }
            }
        }

        return false;
    }

}
