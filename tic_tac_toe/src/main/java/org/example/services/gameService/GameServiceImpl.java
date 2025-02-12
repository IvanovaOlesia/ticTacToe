package org.example.services.gameService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.datasource.mapper.GameMapper;
import org.example.datasource.repository.GameRepository;
import org.example.domain.model.Game;
import org.example.domain.model.Position;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final MoveService moveService;

    @Override
    public Game newGame() {
        return GameMapper.fromEntity(gameRepository.save(GameMapper.toEntity(new Game())));
    }

    @Override
    public void getNextMove(Game game) {
        Position position = moveService.bestMove(game.getBoard());
        if (position.getCol() == -1) return;
        game.getBoard()[position.getRow()][position.getCol()] = MoveService.COMPUTER;
        gameRepository.save(GameMapper.toEntity(game));
    }

    @Override
    public boolean isValidGameBoard(UUID id, Game game) {
        Game currentGame = GameMapper.fromEntity(gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Game with id %s not found", id))));
        return BoardUtils.isValidBoard(currentGame.getBoard(), game.getBoard());

    }
    @Override
    public int isWin(int [][] board){
        return GameEvaluator.evaluateBoard(board);
    }
    @Override
    public boolean isBoardFull(int[][] board){
        return BoardUtils.isBoardFull(board);
    }






}
