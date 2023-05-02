package org.plugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.plugin.service.Message_Service;
import org.plugin.service.Money_Service;

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
                            p.sendMessage(message.getInfo()+"자신의 현재 잔액 : "+money);
                        }else{
                            p.sendMessage(message.getInfo()+" 오류가 발생하여 관리자에게 문의 주세요.");
                        }
                    }
                }else if(args[0].equals("설정")){
                    money_service.admin_chageMoney(args[1],Integer.parseInt(args[2]));
                }else if(args[0].equals("빼기")){

                }else if(args[0].equals("더하기")){

                }else{
                    p.sendMessage(message.getInfo()+"잘못된 명령어입니다.");
                }
            }
        }else{
            //유저
            if(args.length == 0){
                p.sendMessage(message.getInfo()+"/돈 확인 - 자신의 현재 잔액을 확인합니다.");
                p.sendMessage(message.getInfo()+"/돈 보내기 [닉네임] - 다른 사람들에게 돈을 보냅니다.");
                p.sendMessage(message.getInfo()+"/돈 순위 - 현재 순위를 봅니다.");
            }else{
                if(args[0].equals("확인")){
                    int money = money_service.check_Money(p.getName());
                    if(money != -1){
                        p.sendMessage(message.getInfo()+"자신의 현재 잔액 : "+money);
                    }else{
                        p.sendMessage(message.getInfo()+" 오류가 발생하여 관리자에게 문의 주세요.");
                    }
                }else if(args[0].equals("보내기")){

                }else if(args[0].equals("순위")){

                }else{
                    p.sendMessage(message.getInfo()+"잘못된 명령어입니다.");
                }
            }
        }

        return false;
    }

}
